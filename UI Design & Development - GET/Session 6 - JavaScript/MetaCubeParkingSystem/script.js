
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
    const vehicleForm = document.getElementById('vehicleForm');
    const vehicleLabel = document.getElementById('vehicleLabel');
    const vehicleInput = document.getElementById('vehicleInput');
    const passSection = document.getElementById('passSection');
    const passType = document.getElementById('passType');
    const finalPrice = document.getElementById('finalPrice');

    const pricingUSD = {
      Cycle: [0.06, 1.2, 6],
      MotorCycle: [0.12, 2.4, 12],
      "Four Wheelers": [0.24, 6, 42]
    };

    let currentCurrency = 'INR';
    const exchangeRates = { INR: 83, USD: 1, YEN: 156 };

    function convertToCurrency(usd, currency) {
      return (usd * exchangeRates[currency]).toFixed(2);
    }

    const isValidName = name => name.length >= 2 && !/\d/.test(name);
    const isValidEmail = email => email.includes('@') && email.includes('.') && email.length > 5;
    const isValidPassword = pwd =>
      /[a-z]/.test(pwd) &&
      /[A-Z]/.test(pwd) &&
      /\d/.test(pwd) &&
      /[A-Za-z0-9]/.test(pwd) &&
      pwd.length >= 8;
    const isValidContact = contact => /^\d{9,}$/.test(contact);

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
      if (e.key === 'Enter' && input.value.trim() !== '') {
        if (step === 0 && isValidName(input.value)) {
          data.name = input.value;
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
          if (e.key === 'Enter' && isValidEmail(input.value)) {
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
              if (e.key === 'Enter' && isValidPassword(input.value)) {
                data.password = input.value;
                input.removeEventListener('keydown', handlePassword);
                input.classList.remove('password-weak', 'password-normal', 'password-strong');

                input.type = 'password';
                input.placeholder = 'Confirm password';
                label.textContent = 'Confirm your password';
                input.value = '';

                const handleConfirm = (e) => {
                  if (e.key === 'Enter' && input.value === data.password) {
                    input.removeEventListener('keydown', handleConfirm);

                    input.type = 'tel';
                    input.placeholder = 'Enter contact number';
                    label.textContent = 'Enter your contact number';
                    input.value = '';

                    const handleContact = (e) => {
                      if (e.key === 'Enter' && isValidContact(input.value)) {
                        data.contact = input.value;
                        input.removeEventListener('keydown', handleContact);

                        input.classList.add('hidden');
                        label.classList.add('hidden');
                        finalMessage.classList.remove('hidden');
                        const empId = 'EMP' + Math.floor(1000 + Math.random() * 9000);
                        data.empId = empId;
                        document.getElementById('regId').textContent = empId;

                        document.getElementById('addvechicle').style.display = 'flex';
                        // Proceed to vehicle form
                        vehicleForm.classList.remove('hidden');
                        vehicleInput.focus();

                        let vehicleStep = 0;
                        vehicleInput.addEventListener('keydown', function vehicleHandler(e) {
                          if (e.key === 'Enter' && vehicleInput.value.trim() !== '') {
                            if (vehicleStep === 0) {
                              data.vehicleName = vehicleInput.value;
                              vehicleInput.type = 'select';
                              vehicleInput.classList.add('hidden');
                              const select = document.createElement('select');
                              select.id = 'vehicleType';
                              ['Cycle', 'MotorCycle', 'Four Wheelers'].forEach(v => {
                                const opt = document.createElement('option');
                                opt.value = v;
                                opt.textContent = v;
                                select.appendChild(opt);
                              });
                              vehicleLabel.textContent = 'Which vehicle do you have?';
                              vehicleLabel.insertAdjacentElement('afterend', select);
                              vehicleStep++;

                              select.addEventListener('change', () => {
                                data.vehicleType = select.value;
                                select.remove();
                                vehicleLabel.textContent = 'Enter vehicle number:';
                                vehicleInput.classList.remove('hidden');
                                vehicleInput.type = 'text';
                                vehicleInput.value = '';
                                vehicleStep++;

                                vehicleInput.addEventListener('keydown', function handleVehicleNum(e) {
                                  if (e.key === 'Enter' && vehicleInput.value.trim() !== '') {
                                    data.vehicleNumber = vehicleInput.value;
                                    vehicleInput.removeEventListener('keydown', handleVehicleNum);
                                    vehicleInput.value = '';
                                    vehicleLabel.textContent = 'Any identification for vehicle?';
                                    vehicleInput.placeholder = 'Details...';
                                    vehicleStep++;

                                    vehicleInput.addEventListener('keydown', function handleIdent(e) {
                                      if (e.key === 'Enter' && vehicleInput.value.trim() !== '') {
                                        data.identification = vehicleInput.value;
                                        vehicleInput.removeEventListener('keydown', handleIdent);
                                        vehicleForm.classList.add('hidden');
                                        passSection.classList.remove('hidden');

                                        ['Daily', 'Monthly', 'Yearly'].forEach((p, idx) => {
                                          const opt = document.createElement('option');
                                          opt.value = idx;
                                          opt.textContent = `${p} - ${convertToCurrency(pricingUSD[data.vehicleType][idx], currentCurrency)} ${currentCurrency}`;
                                          passType.appendChild(opt);
                                        });
                                      }
                                    });
                                  }
                                });
                              });
                            }
                          }
                        });
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

    function setCurrency(currency) {
      currentCurrency = currency;
      passType.innerHTML = '<option value="">-- Select --</option>';
      if (data.vehicleType) {
        ['Daily', 'Monthly', 'Yearly'].forEach((p, idx) => {
          const opt = document.createElement('option');
          opt.value = idx;
          opt.textContent = `${p} - ${convertToCurrency(pricingUSD[data.vehicleType][idx], currentCurrency)} ${currency}`;
          passType.appendChild(opt);
        });
      }
    }

    function showFinalPrice() {
      const selectedIndex = passType.value;
      if (selectedIndex !== '') {
        const usdPrice = pricingUSD[data.vehicleType][selectedIndex];
        finalPrice.textContent = `Your final pass price (in USD): $${usdPrice}`;
      }
    }

