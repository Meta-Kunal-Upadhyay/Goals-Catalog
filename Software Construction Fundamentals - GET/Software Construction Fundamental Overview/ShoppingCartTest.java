import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Item {
    private int itemId;
    private String name;
    private String desc;
    private double price;
    private int quantity;

    public Item(int itemId, String name, String desc, double price, int quantity){
        this.itemId = itemId;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getdesc() {
        return desc;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int quantity){
        this.quantity -= quantity;
    }

    public void increaseQuantity(int quantity){
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return "ID: " + itemId + ", Name: " + name + ", Price: $" + price + ", Quantity: " + quantity + ", Desc: " + desc;
    }
}

class ShoppingCart {
    private Map<Item, Integer> cart;

    public ShoppingCart() {
        this.cart = new HashMap<>();
    }

    public void addToCart(Item item, Integer quantity) {
        if(quantity <= 0){
            System.out.println("Quantity must be greater than 0.");
            return ;
        }
        if(item.getQuantity() < quantity){
            System.out.println("Not enough quantity available !!");
            return ;
        }

        cart.put(item, cart.getOrDefault(item, 0) + quantity);
        item.reduceQuantity(quantity);
        System.out.println("Added " + quantity + " of " + item.getName() + " to cart.");
    }

    public Integer displayQty(Item item){
        return cart.getOrDefault(item, 0);
    }

    public void updateQty(Item item, Integer quantity) {
        if(!cart.containsKey(item)) {
            System.out.println("Item not found in cart!!");
            return ;
        }
        if(quantity <= 0){
            System.out.println("Quantity must be greater than 0.");
            return ;
        }
        int currentQty = cart.get(item);
        if(quantity > currentQty) {
            int additionalNeeded = quantity - currentQty;
            if(item.getQuantity() < additionalNeeded) {
                System.out.println("Not enough quantity available in the shop!!");
                return ;
            }
            item.reduceQuantity(additionalNeeded);
        } else {
            item.increaseQuantity(currentQty - quantity);
        }
        cart.put(item, quantity);
        System.out.println("Updated " + item.getName() + " quantity to " + quantity + ".");
    }

    public void deleteItem(Item item) {
        if(cart.containsKey(item)) {
            item.increaseQuantity(cart.get(item));
            cart.remove(item);
            System.out.println(item.getName() + " removed from the cart.");
        } else {
            System.out.println(" Item not found in the cart.");
        }
    }

    public double displayBill() {
        double total = 0;
        for(Map.Entry<Item, Integer> entry : cart.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }

        return total;
    }

    public void displayCart() {
        if(cart.isEmpty()){
            System.out.println("Your cart is empty!!");
            return ;
        }
        System.out.println("ITems in your cart: ");
        for(Map.Entry<Item, Integer> entry : cart.entrySet()) {
            System.out.println(entry.getKey() + ", Quantity: " + entry.getValue());
        }
    }
}

public class ShoppingCartTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        Map<Integer, Item> storeItems = new HashMap<>();
        storeItems.put(1, new Item(1, "Laptop", "Device", 900, 10));
        storeItems.put(2, new Item(2, "Phone", "Device", 600, 14));
        storeItems.put(3, new Item(3, "EarPhones", "Sound Device", 150, 20));

        while(true){

            System.out.println("\n******** Shopping Cart Menu ************");
            System.out.println("1. Add Item to Shop");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. Show Item Quantity in Cart");
            System.out.println("4. Update Item Quantity in Cart");
            System.out.println("5. Delete Item from Cart");
            System.out.println("6. Display Cart Total Bill");
            System.out.println("7. Display Cart Contents");
            System.out.println("8. Display All Shop Items");
            System.out.println("9. Exit");
            System.out.println("Enter your choice: ");
            
            int choice = sc.nextInt();

            switch(choice){
                case 1: {
                    System.out.println("Enter Item ID: ");
                    int itemId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Item Name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter Item Desc: ");
                    String desc = sc.nextLine();
                    System.out.println("Enter Item Price: ");
                    double price = sc.nextDouble();
                    System.out.println("Enter Item Quantity: ");
                    int quantity = sc.nextInt();

                    Item newItem = new Item(itemId, name, desc, price, quantity);
                    storeItems.put(itemId, newItem);
                    System.out.println("New item added to the shop!!" + itemId);
                    break;
                
                }
        
                case 2:
                    System.out.println("Available items:");
                    for(Item item : storeItems.values()) {
                        System.out.println(item);
                    }
                    System.out.println("Enter Item ID to add: ");
                    int itemId = sc.nextInt();
                    if(!storeItems.containsKey(itemId)){
                        System.out.println("Item not found");
                        break;
                    }
                    Item item = storeItems.get(itemId);
                    System.out.println("Selected Item: " + item);
                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();
                    cart.addToCart(item, quantity);
                    break;
        
                case 3:
                    System.out.println("Enter Item ID to check quantity: ");
                    int itemId1 = sc.nextInt();
                    if(!storeItems.containsKey(itemId1)){
                        System.out.println("Item not found");
                        break;
                    }
                    Item item12 = storeItems.get(itemId1);
                    System.out.println("Quantity in cart: " + cart.displayQty(item12));
                    break;
        
                case 4:
                    System.out.println("Enter Item ID to update quantity: ");
                    int itemId12 = sc.nextInt();
                    if(!storeItems.containsKey(itemId12)){
                        System.out.println("Item not found");
                        break;
                    }
                    Item item13 = storeItems.get(itemId12);
                    System.out.println("Enter new quantity: ");
                    int quantity1 = sc.nextInt();
                    cart.updateQty(item13, quantity1);
                    break;
                
                case 5: 
                    System.out.println("Enter Item ID to delete: ");
                    int itemId14 = sc.nextInt();
                    if(!storeItems.containsKey(itemId14)){
                        System.out.println("Item not found");
                        break;
                    }
                    Item item14 = storeItems.get(itemId14);
                    cart.deleteItem(item14);
                    break;

                case 6: 
                    System.out.println("Total Bill : $" + cart.displayBill());
                    break;

                case 7: 
                    cart.displayCart();
                    break;

                case 8: 
                    System.out.println("All Shop Items:");
                    for(Item item15 : storeItems.values()) {
                        System.out.println(item15);
                    }
                    break;

                case 9: 
                    System.out.println("Thank You For Shopping!!");
                    sc.close();
                    return ;
                
                default:
                    System.out.println("Invalid Choice!!");
            }
        }
    }
}