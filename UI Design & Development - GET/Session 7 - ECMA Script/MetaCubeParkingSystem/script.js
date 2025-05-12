// Alert message
alert("Welcome To MetaCube Parking System");

const coll = document.getElementsByClassName("collapsible");

Array.from(coll).forEach((item) => {
  item.addEventListener("click", function () {
    this.classList.toggle("active");
    const content = this.nextElementSibling;
    console.log(content);
    content.style.display = content.style.display === "block" ? "none" : "block";
  });
});

let step = 0;
const data = {};
const label = document.getElementById("label");
const input = document.getElementById("inputField");
const genderField = document.getElementById("genderField");
const userNameSpan = document.getElementById("userNameSpan");
const finalMessage = document.getElementById("finalMessage");
const vehicleForm = document.getElementById("vehicleForm");
const vehicleLabel = document.getElementById("vehicleLabel");
const vehicleInput = document.getElementById("vehicleInput");
const passSection = document.getElementById("passSection");
const passType = document.getElementById("passType");
const finalPrice = document.getElementById("finalPrice");

const pricingUSD = {
  Cycle: [0.06, 1.2, 6],
  MotorCycle: [0.12, 2.4, 12],
  "Four Wheelers": [0.24, 6, 42],
};

let currentCurrency = "INR";
const exchangeRates = { INR: 83, USD: 1, YEN: 156 };

const convertToCurrency = (usd, currency) => (usd * exchangeRates[currency]).toFixed(2);

const isValidName = (name) => name.length >= 2 && !/\d/.test(name);
const isValidEmail = (email) => email.includes("@") && email.includes(".") && email.length > 5;
const isValidPassword = (pwd) =>
  /[a-z]/.test(pwd) &&
  /[A-Z]/.test(pwd) &&
  /\d/.test(pwd) &&
  /[A-Za-z0-9]/.test(pwd) &&
  pwd.length >= 8;

const isValidContact = (contact) => /^\d{9,}$/.test(contact);

const passwordStrength = (pwd) => {
  if (pwd.length < 6) return "weak";
  if (/[A-Za-z]/.test(pwd) && /\d/.test(pwd) && pwd.length >= 8) return "strong";
  return "normal";
};

const updatePasswordBorder = (strength) => {
  input.classList.remove("password-weak", "password-normal", "password-strong");
  input.classList.add(`password-${strength}`);
};

input.addEventListener("keydown", (e) => {
  if (e.key === "Enter" && input.value.trim() !== "") {
    if (step === 0 && isValidName(input.value)) {
      data.name = input.value;
      userNameSpan.textContent = data.name.split(" ")[0];
      input.classList.add("hidden");
      label.classList.add("hidden");
      genderField.classList.remove("hidden");
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
    
    Object.assign(input, {
      type: 'email',
      placeholder: 'Enter your email',
      value: ''
    });
    
    label.textContent = 'Please enter your email address:';
    step++;

    const handleEmail = (e) => {
      if (e.key === 'Enter' && isValidEmail(input.value)) {
        data.email = input.value;
        input.removeEventListener('keydown', handleEmail);
        
        Object.assign(input, {
          type: 'password',
          placeholder: 'Enter your password',
          value: ''
        });

        label.textContent = 'Set your password:';
        step++;

        input.addEventListener('input', () => updatePasswordBorder(passwordStrength(input.value)));

        const handlePassword = (e) => {
          if (e.key === 'Enter' && isValidPassword(input.value)) {
            data.password = input.value;
            input.removeEventListener('keydown', handlePassword);
            input.classList.remove('password-weak', 'password-normal', 'password-strong');
            
            Object.assign(input, {
              type: 'password',
              placeholder: 'Confirm password',
              value: ''
            });

            label.textContent = 'Confirm your password';

            const handleConfirm = (e) => {
              if (e.key === 'Enter' && input.value === data.password) {
                input.removeEventListener('keydown', handleConfirm);
                
                Object.assign(input, {
                  type: 'tel',
                  placeholder: 'Enter contact number',
                  value: ''
                });

                label.textContent = 'Enter your contact number';

                const handleContact = (e) => {
                  if (e.key === 'Enter' && isValidContact(input.value)) {
                    data.contact = input.value;
                    input.removeEventListener('keydown', handleContact);

                    input.classList.add('hidden');
                    label.classList.add('hidden');
                    finalMessage.classList.remove('hidden');

                    data.empId = `EMP${Math.floor(1000 + Math.random() * 9000)}`;
                    document.getElementById('regId').textContent = data.empId;

                    document.getElementById('addvechicle').style.display = 'flex';
                    vehicleForm.classList.remove('hidden');
                    vehicleInput.focus();
                    
                    let vehicleStep = 0;
                    vehicleInput.addEventListener('keydown', function vehicleHandler(e) {
                      if (e.key === 'Enter' && vehicleInput.value.trim()) {
                        if (vehicleStep === 0) {
                          data.vehicleName = vehicleInput.value;

                          vehicleInput.classList.add('hidden');
                          vehicleLabel.textContent = 'Which vehicle do you have?';

                          const select = document.createElement('select');
                          select.id = 'vehicleType';
                          ['Cycle', 'MotorCycle', 'Four Wheelers'].forEach(v => {
                            select.appendChild(Object.assign(document.createElement('option'), { value: v, textContent: v }));
                          });

                          vehicleLabel.insertAdjacentElement('afterend', select);
                          vehicleStep++;

                          select.addEventListener('change', () => {
                            data.vehicleType = select.value;
                            select.remove();
                            vehicleLabel.textContent = 'Enter vehicle number:';
                            vehicleInput.classList.remove('hidden');
                            vehicleInput.value = '';
                            vehicleStep++;

                            vehicleInput.addEventListener('keydown', function handleVehicleNum(e) {
                              if (e.key === 'Enter' && vehicleInput.value.trim()) {
                                data.vehicleNumber = vehicleInput.value;
                                vehicleInput.removeEventListener('keydown', handleVehicleNum);
                                vehicleInput.value = '';
                                vehicleLabel.textContent = 'Any identification for vehicle?';
                                vehicleStep++;

                                vehicleInput.addEventListener('keydown', function handleIdent(e) {
                                  if (e.key === 'Enter' && vehicleInput.value.trim()) {
                                    data.identification = vehicleInput.value;
                                    vehicleInput.removeEventListener('keydown', handleIdent);
                                    vehicleForm.classList.add('hidden');
                                    passSection.classList.remove('hidden');

                                    ['Daily', 'Monthly', 'Yearly'].forEach((p, idx) => {
                                      passType.appendChild(
                                        Object.assign(document.createElement('option'), {
                                          value: idx,
                                          textContent: `${p} - ${convertToCurrency(pricingUSD[data.vehicleType][idx], currentCurrency)} ${currentCurrency}`
                                        })
                                      );
                                    });

                                    // Add event listener for final price calculation
                                    let value = passType.addEventListener("change", showFinalPrice);

                                    
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

const setCurrency = (currency) => {
  currentCurrency = currency;
  passType.innerHTML = '<option value="">-- Select --</option>';

  if (data.vehicleType) {
    ['Daily', 'Monthly', 'Yearly'].forEach((p, idx) => {
      passType.appendChild(
        Object.assign(document.createElement('option'), {
          value: idx,
          textContent: `${p} - ${convertToCurrency(pricingUSD[data.vehicleType][idx], currentCurrency)} ${currency}`
        })
      );
    });
  }
};

const showFinalPrice = () => {
   console.log("Get Pass button clicked!");
   const selectedIndex = passType.value;
   console.log("Selected pass type index:", selectedIndex);
   console.log("Vehicle type:", data.vehicleType);
   console.log("Price lookup result:", pricingUSD[data.vehicleType]?.[selectedIndex]);

   if (selectedIndex !== '' && pricingUSD[data.vehicleType]) {
      finalPrice.textContent = `Your final pass price (in ${currentCurrency}): ${convertToCurrency(pricingUSD[data.vehicleType][selectedIndex], currentCurrency)} ${currentCurrency}`;
   }
};
