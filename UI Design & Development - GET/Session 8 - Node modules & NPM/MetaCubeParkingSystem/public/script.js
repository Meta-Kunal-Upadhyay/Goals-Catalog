
// alert("Welcome To MetaCube Parking System");

var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var content = this.nextElementSibling;
    console.log(content);
    if (content.style.display === "block") {
      content.style.display = "none";
    } else {
      content.style.display = "block";
    }
  });
}


let step = 0;
    const data = {};

    const label = document.getElementById('label');
    const input = document.getElementById('inputField');
    const genderField = document.getElementById('genderField');
    const userNameSpan = document.getElementById('userNameSpan');
    const finalMessage = document.getElementById('finalMessage');
    const vehicleSection = document.getElementById('vehicleSection');
    const vehicleInput = document.getElementById('vehicleInput');
    const vehicleLabel = document.getElementById('vehicleLabel');
    const passSection = document.getElementById('passSection');
    const passType = document.getElementById('passType');
    const currency = document.getElementById('currency');
    const getPassBtn = document.getElementById('getPass');
    const passPrice = document.getElementById('passPrice');

    const ratesUSD = {
      Cycle: { daily: 0.06, monthly: 1.2, yearly: 6 },
      MotorCycle: { daily: 0.12, monthly: 2.4, yearly: 12 },
      "Four Wheelers": { daily: 0.24, monthly: 6, yearly: 42 }
    };

    const exchangeRates = {
      INR: 83,
      USD: 1,
      YEN: 156
    };

    const validateName = (name) => name.length >= 2 && isNaN(name);
    const validateEmail = (email) => email.includes('@') && email.includes('.') && email.length >= 5;
    const validatePassword = (pwd) => /[A-Z]/.test(pwd) && /[a-z]/.test(pwd) && /\d/.test(pwd) && /[A-Za-z\d]/.test(pwd) && pwd.length >= 8;
    const validateContact = (num) => /^\d{9,}$/.test(num);

    const passwordStrength = (pwd) => {
      if (pwd.length < 6) return 'weak';
      if (/[A-Za-z]/.test(pwd) && /\d/.test(pwd) && pwd.length >= 8) return 'strong';
      return 'normal';
    };

    const updatePasswordBorder = (strength) => {
      input.classList.remove('password-weak', 'password-normal', 'password-strong');
      if (strength === 'weak') input.classList.add('password-weak');
      else if (strength === 'normal') input.classList.add('password-normal');
      else if (strength === 'strong') input.classList.add('password-strong');
    };

    input.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' && validateName(input.value.trim())) {
        if (step === 0) {
          data.name = input.value.trim();
          userNameSpan.textContent = data.name.split(" ")[0];
          input.classList.add('hidden');
          label.classList.add('hidden');
          genderField.classList.remove('hidden');
          step++;
        }
      }
    });

    document.querySelectorAll('input[name="gender"]').forEach(radio => {
      radio.addEventListener('change', () => {
        data.gender = radio.value;

        genderField.classList.add('hidden');
        input.classList.remove('hidden');
        label.classList.remove('hidden');
        input.type = 'email';
        input.placeholder = 'Enter your email';
        label.textContent = 'Please enter your email address:';
        input.value = '';
        step++;

        const handleEmail = (e) => {
          if (e.key === 'Enter' && validateEmail(input.value)) {
            data.email = input.value;
            input.removeEventListener('keydown', handleEmail);

            input.type = 'password';
            input.placeholder = 'Enter your password';
            label.textContent = 'Set your password:';
            input.value = '';
            step++;

            input.addEventListener('input', () => {
              const strength = passwordStrength(input.value);
              updatePasswordBorder(strength);
            });

            const handlePassword = (e) => {
              if (e.key === 'Enter' && validatePassword(input.value)) {
                data.password = input.value;
                input.removeEventListener('keydown', handlePassword);
                input.classList.remove('password-weak', 'password-normal', 'password-strong');

                input.type = 'password';
                input.placeholder = 'Confirm password';
                label.textContent = 'Confirm your password';
                input.value = '';

                const handleConfirm = (e) => {
                  if (e.key === 'Enter' && input.value === data.password) {
                    data.confirmPassword = input.value;
                    input.removeEventListener('keydown', handleConfirm);

                    input.type = 'tel';
                    input.placeholder = 'Enter contact number';
                    label.textContent = 'Enter your contact number';
                    input.value = '';

                    const handleContact = (e) => {
                      if (e.key === 'Enter' && validateContact(input.value)) {
                        data.contact = input.value;
                        input.removeEventListener('keydown', handleContact);

                        input.classList.add('hidden');
                        label.classList.add('hidden');
                        finalMessage.classList.remove('hidden');
                        document.getElementById('regId').textContent =
                          data.empId = 'EMP' + Math.floor(1000 + Math.random() * 9000);

                        vehicleSection.classList.remove('hidden');
                      }
                    };

                    input.addEventListener('keydown', handleContact);
                  }
                };

                input.addEventListener('keydown', handleConfirm);
              }
            };

            input.addEventListener('keydown', handlePassword);
          }
        };

        input.addEventListener('keydown', handleEmail);
      });
    });

    vehicleInput.addEventListener('keydown', (e) => {
      if (e.key === 'Enter' && vehicleInput.value.trim()) {
        data.vehicleName = vehicleInput.value.trim();
        vehicleLabel.textContent = 'Which vehicle do you have?';
        vehicleInput.classList.add('hidden');

        const vehicleSelect = document.createElement('select');
        vehicleSelect.innerHTML = `
          <option value="Cycle">Cycle</option>
          <option value="MotorCycle">MotorCycle</option>
          <option value="Four Wheelers">Four Wheelers</option>
        `;
        vehicleLabel.after(vehicleSelect);

        vehicleSelect.addEventListener('change', () => {
          data.vehicleType = vehicleSelect.value;
          passSection.classList.remove('hidden');
        });
      }
    });

    getPassBtn.addEventListener('click', () => {
      const type = passType.value;
      const cur = currency.value;
      const rateInUSD = ratesUSD[data.vehicleType][type];
      const displayRate = (rateInUSD * exchangeRates[cur]).toFixed(2);
      passPrice.textContent = `Your ${type} pass price in ${cur} is ${displayRate}`;
    });