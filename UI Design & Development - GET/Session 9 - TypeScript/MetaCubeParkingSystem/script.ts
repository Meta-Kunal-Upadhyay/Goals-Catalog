const coll = document.getElementsByClassName("collapsible") as HTMLCollectionOf<HTMLElement>;

for (let i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function () {
    this.classList.toggle("active");

    const content: HTMLElement | null = this.nextElementSibling as HTMLElement;
    console.log(content);

    if (content) {
      content.style.display = content.style.display === "block" ? "none" : "block";
    }
  });
}

class Employee {
    private empId: string;
    private name: string;
    private email: string;
    private contact: string;
  
    constructor(name: string, email: string, contact: string) {
      this.empId = `EMP${Math.floor(1000 + Math.random() * 9000)}`;
      this.name = name;
      this.email = email;
      this.contact = contact;
    }
  
    getDetails(): string {
      return `Employee ID: ${this.empId}, Name: ${this.name}, Email: ${this.email}, Contact: ${this.contact}`;
    }
  }
  
  class Vehicle {
    protected vehicleName: string;
    protected vehicleType: string;
  
    constructor(vehicleName: string, vehicleType: string) {
      this.vehicleName = vehicleName;
      this.vehicleType = vehicleType;
    }
  
    getVehicleInfo(): string {
      return `Vehicle: ${this.vehicleName}, Type: ${this.vehicleType}`;
    }
  }
  
  class Pass {
    protected passType: string;
    protected currency: string;
    protected price: number;
  
    constructor(passType: string, currency: string, price: number) {
      this.passType = passType;
      this.currency = currency;
      this.price = price;
    }
  
    getPassDetails(): string {
      return `Pass Type: ${this.passType}, Currency: ${this.currency}, Price: ${this.price}`;
    }
  }
  
  class VehiclePass extends Pass {
    private vehicle: Vehicle;
  
    constructor(vehicle: Vehicle, passType: string, currency: string, price: number) {
      super(passType, currency, price);
      this.vehicle = vehicle;
    }
  
    getCompletePassInfo(): string {
      return `${this.getPassDetails()}, ${this.vehicle.getVehicleInfo()}`;
    }
  }
  
  // UI Logic
  const inputField = document.getElementById("inputField") as HTMLInputElement;
  const label = document.getElementById("label") as HTMLLabelElement;
  const genderField = document.getElementById("genderField") as HTMLDivElement;
  const userNameSpan = document.getElementById("userNameSpan") as HTMLSpanElement;
  const finalMessage = document.getElementById("finalMessage") as HTMLDivElement;
  const regId = document.getElementById("regId") as HTMLSpanElement;
  const vehicleSection = document.getElementById("vehicleSection") as HTMLDivElement;
  const vehicleInput = document.getElementById("vehicleInput") as HTMLInputElement;
  const passSection = document.getElementById("passSection") as HTMLDivElement;
  const passType = document.getElementById("passType") as HTMLSelectElement;
  const currency = document.getElementById("currency") as HTMLSelectElement;
  const getPassBtn = document.getElementById("getPass") as HTMLButtonElement;
  const passPrice = document.getElementById("passPrice") as HTMLDivElement;
  
  let employee: Employee;
  let vehicle: Vehicle;
  let userData: { name?: string; vehicleName?: string; vehicleType?: string } = {};
  
  inputField.addEventListener("keydown", (e) => {
    if (e.key === "Enter" && inputField.value.trim().length > 2) {
      userData.name = inputField.value.trim();
      userNameSpan.textContent = userData.name.split(" ")[0];
      employee = new Employee(userData.name, "user@example.com", "1234567890");
      regId.textContent = employee.getDetails();
      
      inputField.classList.add("hidden");
      label.classList.add("hidden");
      genderField.classList.remove("hidden");
    }
  });
  
  vehicleInput.addEventListener("keydown", (e) => {
    if (e.key === "Enter" && vehicleInput.value.trim()) {
      userData.vehicleName = vehicleInput.value.trim();
      vehicle = new Vehicle(userData.vehicleName, "Four Wheelers");
      
      vehicleSection.classList.add("hidden");
      passSection.classList.remove("hidden");
    }
  });
  
  getPassBtn.addEventListener("click", () => {
    const type = passType.value;
    const cur = currency.value;
    const price = type === "daily" ? 0.5 : type === "monthly" ? 10 : 100;
    
    const pass = new VehiclePass(vehicle, type, cur, price);
    passPrice.textContent = pass.getCompletePassInfo();
  });