// Program that creates 2 players in one java process (PID)  

import java.util.Scanner;

public class Players {
    private static final int MESSAGE_LIMIT = 10;
    private int sent_messageCounter = 0;
    private int received_messageCounter = 0;
    private String name;

    public Players(String name) {
        this.name = name;
    }

    // Method for sending a message
    public String sendMessage(Scanner scanner) {
        // Check if the number of messages sent is within the limit
        if (sent_messageCounter < MESSAGE_LIMIT) {
            System.out.println("Enter your message:");
            String message = scanner.nextLine();
            sent_messageCounter++;
            return message;
        } else {
            // Inform the player that they cannot send more messages
            System.out.println(name + " cannot send more messages.");
            return null;
        }
    }

    // Method for receiving a message / confirm receiving the message along with the receiver's message count
    public void receiveMessage(String input) {
        // Check if the number of messages received is within the limit
        if (received_messageCounter < MESSAGE_LIMIT) {
            System.out.println(name + " received : " + input);
            System.out.println(name + " confirms receiving : " + input + " (Messages sent from me: " + sent_messageCounter + ")");
            received_messageCounter++;
        } else {
            // Inform the player that they cannot receive more messages
            System.out.println(name + " cannot receive more messages.");
        }
    }

    // Method to check if messages can still be sent or received
    public boolean check() {
        return sent_messageCounter < MESSAGE_LIMIT || received_messageCounter < MESSAGE_LIMIT;
    }

    public static void main(String[] args) {
        // Creating 2 players from the class Players
        Players player1 = new Players("Initiator");
        Players player2 = new Players("Player 2");

        Scanner sc = new Scanner(System.in);

        //Initiator sending the first message
        System.out.println("Initiator's first message:");
        String input1 = player1.sendMessage(sc);
        player2.receiveMessage(input1);

        while (player1.check()) {

            // Prompt for the player number who will send the next message
            System.out.println("Which player is sending a message now? (1: Initiator / 2: Player 2)");
            int playernum = sc.nextInt();
            sc.nextLine(); 

            // Process the message based on the player number chosen
            if (playernum == 1) {
                input1 = player1.sendMessage(sc);
                player2.receiveMessage(input1);
            } else if (playernum == 2) {
                String input2 = player2.sendMessage(sc);
                player1.receiveMessage(input2);
            } else {
                // Handle the case of an invalid player number input
                System.out.println("Invalid player number.");
            }
        }

        // Close the Scanner after the loop ends
        sc.close();

        //finalizing the program gracefully
        System.out.println("Finalizing the game, thank you for playing...");
		System.exit(0);
    }
}