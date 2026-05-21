import java.util.*;

// Movie Class
class Movie {
    private String movieName;
    private String showTime;

    public Movie(String movieName, String showTime) {
        this.movieName = movieName;
        this.showTime = showTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getShowTime() {
        return showTime;
    }

    public void displayMovie() {
        System.out.println("\nMovie: " + movieName);
        System.out.println("Show Time: " + showTime);
    }
}

class Seat {
    private String type;
    private double price;
    private boolean isBooked;

    public Seat(String type, double price) {
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookSeat() {
        isBooked = true;
    }
}

class Booking {
    private Seat[][] seats;
    private double totalCost;
    private ArrayList<String> bookedSeats;

    public Booking(int rows, int cols) {
        seats = new Seat[rows][cols];
        bookedSeats = new ArrayList<>();
        totalCost = 0;

        // Initialize Seats
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                
                if (i < 2) {
                    seats[i][j] = new Seat("Premium", 200);
                } else {
                    seats[i][j] = new Seat("Regular", 100);
                }
            }
        }
    }

    
    public void displaySeats() {
        System.out.println("\n===== Seat Layout =====");
        System.out.println("O = Available | X = Booked\n");

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {

                if (seats[i][j].isBooked()) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }

    // Book Seat
    public void bookSeat(int row, int col) {

        // Check Valid Position
        if (row < 0 || col < 0 || row >= seats.length || col >= seats[0].length) {
            System.out.println("Invalid seat number!");
            return;
        }

        
        if (seats[row][col].isBooked()) {
            System.out.println("Seat already booked!");
            return;
        }

        // Book Seat
        seats[row][col].bookSeat();

        totalCost += seats[row][col].getPrice();

        bookedSeats.add("(" + (row + 1) + "," + (col + 1) + ")");

        System.out.println("Seat booked successfully!");
        System.out.println("Seat Type: " + seats[row][col].getType());
        System.out.println("Price: ₹" + seats[row][col].getPrice());
    }

    
    public void displaySummary(Movie movie) {

        System.out.println("\n===== Booking Summary =====");

        System.out.println("Movie: " + movie.getMovieName());
        System.out.println("Show Time: " + movie.getShowTime());

        if (bookedSeats.isEmpty()) {
            System.out.println("No seats booked.");
        } else {
            System.out.println("Booked Seats: " + bookedSeats);
            System.out.println("Total Cost: ₹" + totalCost);
            System.out.println("Booking Confirmed!");
        }
    }
}


public class MovieTicketBookingSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Movie movie = new Movie("Avengers: Endgame", "7:00 PM");

        Booking booking = new Booking(5, 5);

        int choice;

        System.out.println("===== Welcome to Movie Ticket Booking System =====");

        movie.displayMovie();

        do {

            booking.displaySeats();

            System.out.println("\n1. Book Seat");
            System.out.println("2. Finish Booking");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Row (1-5): ");
                    int row = sc.nextInt();

                    System.out.print("Enter Column (1-5): ");
                    int col = sc.nextInt();

                    booking.bookSeat(row - 1, col - 1);

                    break;

                case 2:

                    booking.displaySummary(movie);

                    break;

                default:

                    System.out.println("Invalid Choice!");
            }

        } while (choice != 2);

        sc.close();
    }
}