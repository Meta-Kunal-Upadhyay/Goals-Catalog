#include<bits/stdc++.h>
using namespace std;

class Item {
    public:
        int itemId;
        string name;
        string desc;
        double price;
        int quantity;

        Item(int id, string n, string d, double p, int q){
            itemId = id;
            name = n;
            desc = d;
            price = p;
            quantity = q;
        }

        void inputItem(int id){
            itemId = id;
            cout<<"Enter item name: ";
            cin.ignore();
            getline(cin, name);
            cout<<"Enter item description: ";
            getline(cin, description);
            cout<<"Enter item price: ";
            cin >> price;
            cout<<"Enter available quantity: ";
            cin >> quantity;
        }
};

class ShoppingCart {
    private:
        unordered_map<int, pair<Item, int>> cart;

    public:
        void addToCart(Item item, int quantity) {
            if(quantity > item.quantity){
                cout<<"Cannot add more items than available in stocks!!"<<endl;
            }
            else {
                if(cart.find(item) != cart.end()){
                    cart[item.itemID].second += quantity;
                } else {
                    cart[item.itemId] = make_pair(item, quantity);
                }
                cout<<"Item added to cart successfully!!" << endl;
            }
        }

        int displayQty(Item item){
            if(cart.find(item.itemId) != cart.end()){
                return cart[item.itemId].second;
            }

            return 0;
        }

        void updateQty(Item item, int quantity){

            if(quantity > item.quantity) {
                cout<<"Cannot update quantity to more than available in stock!!"<<endl;
            } else {
                if(cart)
            }
        }
}
int main() {
    ShoppingCart cart;
    char ch;

    while(true){

        cout<<"\n******** Shopping Cart Menu ************\n
        A Add Item to Cart\n
        B Show Item Quantity\n
        C Update Item Quantity\n
        D Delete Item from Cart\n
        E Display Cart Total Bill\n
        F Display Cart Contents\n
        G Quit"<<endl;
        cin >> ch;

        switch(ch){
            case 'A': {
                int itemId, quantity;
                cout<< "Enter item ID: ";
                cin>>itemId;
                Item newItem(itemId, "", "", 0);
                newIteam.inputItem(itemId);
                cout<<"Enter quantity to add: ";
                cin>>quantity;
                cart.addToCArt(newIteam, quantity);
                cout<<"Item added to cart successfully!"<<endl;
                break;
            }
    
            case 'B':
    
            case 'u':
    
            case 'di':
        }
    }

    
}