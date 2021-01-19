document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          if (this.validationOfTheStep1(btn) && this.validationOfTheStep2(btn) && this.validationOfTheStep3(btn) && this.validationOfTheStep4(btn)) {
            this.currentStep++;
            this.updateForm();
          }
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      this.$form.querySelectorAll("input").forEach(input => {
        input.addEventListener("keydown", e => {
          if (e.key === "Enter") {
            e.preventDefault();
            this.$form.querySelector("div.active .next-step").click();
          }
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    validationOfTheStep1(btn) {
      if (this.currentStep === 1) {
        const categories = form.querySelectorAll("[name=categories]:checked");
        const error = btn.parentElement.parentElement.querySelector("div.error");
        if (categories.length === 0) {
          error.style.display = "block";
          return false;
        } else if (categories.length !== 0 && error.style.display === "block") {
          error.style.display = "none";
        }
      }
      return true;
    }

    validationOfTheStep2(btn) {
      if (this.currentStep === 2) {
        const quantity = form.querySelector("#quantity");
        const error = btn.parentElement.parentElement.querySelector("div.error");
        if (quantity.value <= 0) {
          error.style.display = "block";
          return false;
        } else if (quantity.value > 0 && error.style.display === "block") {
          error.style.display = "none";
        }
      }
      return true;
    }

    validationOfTheStep3(btn) {
      if (this.currentStep === 3) {
        const institution = form.querySelector("[name=institution]:checked");
        const error = btn.parentElement.parentElement.querySelector("div.error");
        if (institution === null) {
          error.style.display = "block";
          return false;
        }
          error.style.display = "none";
      }
      return true;
    }

    validationOfTheStep4(btn) {
      if (this.currentStep === 4) {
        const checkStreet = this.checkField(btn, "ulicę", "#street", "#error1", /^[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+((\s+[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+)|(-[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+))*\s+(\d+|(\d+(\/\d+)*))$/);
        const checkCity = this.checkField(btn, "nazwę miasta", "#city", "#error2", /^[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+((\s+[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+)|(-[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ]+))*$/);
        const checkZipCode = this.checkField(btn, "kod pocztowy", "#zipCode", "#error3", /^(\d{2}-\d{3})$/);
        const checkPhone = this.checkField(btn, "numer telefonu (9 cyfr bez spacji)", "#phoneNumber", "#error4", /^\d{9}$/);
        const checkDate = this.checkDate(btn, "#pickUpDate", "#error5");
        const checkTime = this.checkTime(btn, "#pickUpTime", "#error6");
        return checkStreet && checkCity && checkZipCode && checkPhone && checkDate && checkTime;
      }
      return true;
    }

    checkField(button, fieldName, fieldSelector, errorSelector, regExp) {
      const field = form.querySelector(fieldSelector).value;
      const error = button.parentElement.parentElement.querySelector(errorSelector);
      if (field.length === 0) {
        error.style.display = "block";
        button.parentElement.parentElement.querySelector(errorSelector).innerText = "Proszę uzupełnić pole";
        return false;
      } else if (!regExp.test(field)) {
        error.style.display = "block";
        button.parentElement.parentElement.querySelector(errorSelector).innerText = "Proszę wpisać poprawnie " + fieldName;
        return false;
      }
      error.style.display = "none";
      return true;
    }

    checkDate(button, fieldSelector, errorSelector) {
      const field = form.querySelector(fieldSelector).value;
      const error = button.parentElement.parentElement.querySelector(errorSelector);
      if (field.length === 0) {
        error.style.display = "block";
        button.parentElement.parentElement.querySelector(errorSelector).innerText = "Proszę uzupełnić pole";
        return false;
      } else if (!this.checkDateIsInPeriod(field)) {
        error.style.display = "block";
        button.parentElement.parentElement.querySelector(errorSelector).innerHTML = "Proszę wpisać poprawnie datę<br />(odbiór można zaplanować najszybciej na jutro, najpóźniej za miesiąc)";
        return false;
      } else if (this.checkDateIsTheWeekend(field)) {
        error.style.display = "block";
        button.parentElement.parentElement.querySelector(errorSelector).innerHTML = "Niestety kurierzy nie pracują w weekend";
        return false;
      }
      error.style.display = "none";
      return true;
    }

    checkDateIsInPeriod(checkingDate) {
      let now = new Date();
      now.setHours(0);
      now.setMinutes(0);
      now.setSeconds(0);

      const checkingDateString = checkingDate.toString();
      const year = checkingDateString.substring(0, 4);
      const month = checkingDateString.substring(5, 7);
      const day = checkingDateString.substring(8, 10);
      let date = new Date(now.getTime());              //problem przy walidacji następnego dnia, czy trzeba tak tu namieszać, próbowałem od razu wstawić
      date.setFullYear(Number(year));                  //checkingDate do konstruktora i wyzerować godzinę bo się dodawało i niby w returnie date i tomorrow były takie same a zwracało false
      date.setMonth(Number(month)-1);
      date.setDate(Number(day));

      let tomorrow = new Date(now.getTime());
      tomorrow.setDate(tomorrow.getDate() + 1);
      let inAMonth = new Date(now.getTime());
      inAMonth.setDate(inAMonth.getDate() + 30);
      return date >= tomorrow && date <= inAMonth;
    }

    checkDateIsTheWeekend(checkingDate) {
      const date = new Date(checkingDate);
      return date.getDay() === 0 || date.getDay() === 6;
    }

    checkTime(button, fieldSelector, errorSelector) {
      const field = form.querySelector(fieldSelector).value;
      console.log(field);
      const error = button.parentElement.parentElement.querySelector(errorSelector);
      if (field.length === 0) {
        error.style.display = "block";
        button.parentElement.parentElement.querySelector(errorSelector).innerText = "Proszę uzupełnić pole.";
        return false;
      } else if (!this.checkTimeIsInPeriod(field)) {
        error.style.display = "block";
        button.parentElement.parentElement.querySelector(errorSelector).innerHTML = "Proszę wpisać poprawnie godzinę<br />(odbiór można zaplanować pomiędzy 8-18)";
        return false;
      }
      error.style.display = "none";
      return true;
    }

    checkTimeIsInPeriod(checkingTime) {
      let date = new Date();
      const hours = checkingTime.toString().substring(0, 2);
      const minutes = checkingTime.toString().substring(3, 5);
      date.setHours(Number(hours));
      date.setMinutes(Number(minutes));
      date.setSeconds(0);
      let hourOfTheFirstPickUp = new Date();
      hourOfTheFirstPickUp.setHours(8);
      hourOfTheFirstPickUp.setMinutes(0);
      hourOfTheFirstPickUp.setSeconds(0);
      let hourOfTheLastPickUp = new Date();
      hourOfTheLastPickUp.setHours(18);
      hourOfTheLastPickUp.setMinutes(0);
      hourOfTheLastPickUp.setSeconds(0);
      console.log(date);
      console.log(hourOfTheFirstPickUp);
      console.log(hourOfTheLastPickUp);
      return date >= hourOfTheFirstPickUp && date <= hourOfTheLastPickUp;
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      this.fillInTheSummary();

    }

    fillInTheSummary() {
      if (this.currentStep === 5) {
        const quantity = Number(form.querySelector("#quantity").value);
        const categories = form.querySelectorAll("[name=categories]:checked ~ .description");
        let categoriesInnerText = [];
        categories.forEach(category => {
          categoriesInnerText.push(category.innerText);
        });
        const checkedCategoriesInnerText = categoriesInnerText.join(", ");
        const content = ". Zawartość: " + checkedCategoriesInnerText;
        let variant;
        if (quantity === 1) {
          variant = " worek";
        } else if (quantity > 1 && quantity < 5) {
          variant = " worki";
        } else {
          variant = " worków";
        }
        form.querySelector("#quantitySummary").innerText = quantity + variant + content;

        const checkedInstitution = form.querySelector("[name=institution]:checked").parentElement.querySelector("div.title").innerText;
        form.querySelector("#institutionSummary").innerText = "Dla: " + checkedInstitution;

        form.querySelector("#streetSummary").innerText = form.querySelector("#street").value;
        form.querySelector("#citySummary").innerText = form.querySelector("#city").value;
        form.querySelector("#zipSummary").innerText = form.querySelector("#zipCode").value;
        form.querySelector("#phoneSummary").innerText = form.querySelector("#phoneNumber").value;
        form.querySelector("#dateSummary").innerText = form.querySelector("#pickUpDate").value;
        form.querySelector("#timeSummary").innerText = form.querySelector("#pickUpTime").value;
        form.querySelector("#commentSummary").innerText = form.querySelector("#pickUpComment").value;
      }
    }
  }

  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
