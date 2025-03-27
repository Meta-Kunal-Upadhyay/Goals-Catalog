import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {

    // Book class for handling book details and availability
    static class Book {
        private String title;
        private String author;
        private boolean isAvailable;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.isAvailable = true; // Initially, the book is available
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
        }

        @Override
        public String toString() {
            return "Book[Title: " + title + ", Author: " + author + ", Available: " + isAvailable + "]";
        }
    }

    // Member class for handling member details and borrowing/returning books
    static class Member {
        private String name;
        private int memberId;
        private List<Book> borrowedBooks;

        public Member(String name, int memberId) {
            this.name = name;
            this.memberId = memberId;
            this.borrowedBooks = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public int getMemberId() {
            return memberId;
        }

        public List<Book> getBorrowedBooks() {
            return borrowedBooks;
        }

        public void borrowBook(Book book) {
            if (book.isAvailable()) {
                book.setAvailable(false);
                borrowedBooks.add(book);
                System.out.println(name + " successfully borrowed the book: " + book.getTitle());
            } else {
                System.out.println("The book " + book.getTitle() + " is currently unavailable.");
            }
        }

        public void returnBook(Book book) {
            if (borrowedBooks.contains(book)) {
                book.setAvailable(true);
                borrowedBooks.remove(book);
                System.out.println(name + " successfully returned the book: " + book.getTitle());
            } else {
                System.out.println("This book is not borrowed by " + name);
            }
        }

        public void listBorrowedBooks() {
            if (borrowedBooks.isEmpty()) {
                System.out.println(name + " has no borrowed books.");
            } else {
                System.out.println(name + " has borrowed the following books:");
                for (Book book : borrowedBooks) {
                    System.out.println("- " + book.getTitle());
                }
            }
        }
    }

    // Library class for managing books and members
    static class Library {
        private List<Book> books;
        private List<Member> members;

        public Library() {
            this.books = new ArrayList<>();
            this.members = new ArrayList<>();
        }

        public void addBook(Book book) {
            books.add(book);
            System.out.println("Book added: " + book.getTitle());
        }

        public void registerMember(Member member) {
            members.add(member);
            System.out.println("Member registered: " + member.getName());
        }

        public Book searchBookByTitle(String title) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    return book;
                }
            }
            System.out.println("Book not found with title: " + title);
            return null;
        }

        public Member searchMemberById(int memberId) {
            for (Member member : members) {
                if (member.getMemberId() == memberId) {
                    return member;
                }
            }
            System.out.println("Member not found with ID: " + memberId);
            return null;
        }

        public void listBooks() {
            System.out.println("Books in the library:");
            for (Book book : books) {
                System.out.println(book);
            }
        }

        public void listMembers() {
            System.out.println("Library Members:");
            for (Member member : members) {
                System.out.println("- " + member.getName() + " (ID: " + member.getMemberId() + ")");
            }
        }
    }

    // Main class to simulate library management
    public static void main(String[] args) {
        // Create a library instance
        Library library = new Library();

        // Add books to the library
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee");
        Book book3 = new Book("1984", "George Orwell");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Register members
        Member member1 = new Member("John Doe", 101);
        Member member2 = new Member("Jane Smith", 102);
        library.registerMember(member1);
        library.registerMember(member2);

        // Display books and members
        library.listBooks();
        library.listMembers();

        // Member borrowing books
        member1.borrowBook(book1); // John borrows "The Great Gatsby"
        member2.borrowBook(book1); // Jane tries to borrow the same book (unavailable)
        member1.borrowBook(book2); // John borrows "To Kill a Mockingbird"

        // Display borrowed books
        member1.listBorrowedBooks();
        member2.listBorrowedBooks();

        // Member returning books
        member1.returnBook(book1); // John returns "The Great Gatsby"
        member2.borrowBook(book1); // Now Jane can borrow "The Great Gatsby"

        // Display borrowed books after returning
        member1.listBorrowedBooks();
        member2.listBorrowedBooks();

        // Search for a book by title
        Book searchedBook = library.searchBookByTitle("1984");
        System.out.println("Searched Book: " + searchedBook);
    }
}
