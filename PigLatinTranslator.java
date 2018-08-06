
//import all that good stuff
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

//introduce class
public class PigLatinTranslator extends JFrame implements ActionListener{

    //userInput to hold the text the user entered
    String userInput;
    //fullTranslation to hold complete translated phrase
    String fullTranslation="";

    //make the textAreas global variable because I need them in the ActionPerformed method
    //textArea is the text the user entered and textArea2 is the display for the translation
    JTextArea textArea= new JTextArea();
    JTextArea textArea2= new JTextArea();

    //main method calls translator
    public static void main(String[] args) {

        new PigLatinTranslator();
    }

    //the input and frame AKA the juicy stuff
    public PigLatinTranslator(){

        //set size of window
        //this window is adjustable
        //the stuff will stay centered and stretch according to window size because of Layouts
        this.setSize(700,600);
        //setting location relative to null to center frame on screen
        this.setLocationRelativeTo(null);
        //setting frame to exit on close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //titling frame
        this.setTitle("Pig Latin Translator");

        //panels hold elements in a frame
        JPanel panel= new JPanel();
        JPanel panel2 = new JPanel();
        JPanel textPanel= new JPanel();
        JPanel buttonPanel= new JPanel();

        //setting up GridLayout panel within GridBagLayout panel2(not in textbook I know but it works best for centering regardless of window resize)
        panel2.setLayout(new GridBagLayout());
        panel.setLayout(new GridLayout(4,1, 20, 20));
        panel2.add(panel);

        //add text panel to Grid layout.
        panel.add(textPanel);


        //creating a label so I can give the user instructions
        JLabel label= new JLabel();

        //entering instructions by setting label text
        label.setText("Enter what you want to be translated.");

        //setting font
        label.setFont(new Font("Arial",Font.PLAIN, 35));

        //add the label to panel so it shows up

        textPanel.add(label);




        //we are creating the text areas now
        //I want this one to be editable
        //so the user can input words to be translated
        textArea.setEditable(true);
        //some formatting stuff~
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(500,200));
        textArea.setFont(new Font("Arial",Font.PLAIN, 20));

        //this one isn't editable because it's the translation
        textArea2.setEditable(false);
        //formatting stuff again
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        textArea2.setPreferredSize(new Dimension(500,200));
        textArea2.setFont(new Font("Arial",Font.PLAIN, 20));

        //add text area to panel
        panel.add(textArea);

        //I want to add a border so the window is properly spaced and not ugly
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        //create button that says "translate"
        JButton translateButton= new JButton("Translate");
        //fixing font bc again it is so tiny
        translateButton.setFont(new Font("Arial", Font.PLAIN, 20));
        //adding an action listener
        //so that once the button is pressed
        //it calls action performed to translate it
        translateButton.addActionListener(this);
        //make button the right size
        translateButton.setBorder(new EmptyBorder(5,5,5,5));

        //add buttonPanel to panel and add button to buttonPanel
        panel.add(buttonPanel);
        buttonPanel.add(translateButton);
        //and finally add textArea2 to panel
        panel.add(textArea2);

        //add the panel to the frame
        this.add(panel);
        //set the visibility to true
        //so that it actually shows up
        this.setVisible(true);

        //end method
    }

    //override whatever was in actionPerformed before
    @Override
    //create action performed method
    public void actionPerformed(ActionEvent e) {
        // set userInput= whatever the user put into text area
        String userInput = textArea.getText();
        //clear last translation
        fullTranslation="";

        //create variables
        //first letter is to test and see if the word
        //begins with a vowel
        //then is later used to move the first letter
        //to the end of the word
        //if it begins with a consonant
        //last letter is to check for punctuation


        String firstLetter;
        String lastLetter;
        //second part is everything after the first letter
        //it is used for the first part of the translation
        String secondPart;
        //we're putting the scanner in here
        //because it needs to be implemented after the user enters
        //what they want translated
        //so that it's not reading a blank
        Scanner scanner= new Scanner(userInput);
        //while loop is to step through each word with scanner

        while (scanner.hasNext()){
            //current word takes the current parsed word
            String currentWord;
            //new word is currentWord, but translated
            String newWord;
            //set current word equal to word in scanner
            currentWord = scanner.next();
            //using substring to slice first letter
            //of current word
            //and set it as firstLetter
            firstLetter= currentWord.substring(0,1);
            lastLetter= currentWord.substring(currentWord.length()-1, currentWord.length());

            //use if statement to figure out
            //if first letter is a vowel or not
            if (firstLetter.equalsIgnoreCase("A")| firstLetter.equalsIgnoreCase("E")|
                    firstLetter.equalsIgnoreCase("I")| firstLetter.equalsIgnoreCase("O")|
                    firstLetter.equalsIgnoreCase("U")| firstLetter.equalsIgnoreCase("Y")){
                //if first letter is a vowel
                //just use currentWord but add an "ay" at the end
                newWord= currentWord+"ay";

                //end if
            }
            //else statement to check for nonalphanumeric word
            else if (firstLetter.matches("^.*[^a-zA-Z0-9].*$")){
                newWord= currentWord;

            }
            else{
                //get the second part of the word (after first letter)
                if (lastLetter.matches("^.*[^a-zA-Z0-9].*$")) {
                    currentWord = currentWord.substring(0,currentWord.length()-1);
                }
                secondPart = currentWord.substring(1, currentWord.length());
                //move the first letter to the back and add an "ay"
                newWord = secondPart + firstLetter + "ay";
                //end else
            }
            //Checks for punctuation and adds it to end of word if present
            if (lastLetter.matches("^.*[^a-zA-Z0-9].*$")){
                newWord= newWord+ lastLetter;
            }
            //combine your newly translated word with the rest of the translation
            fullTranslation= fullTranslation+ newWord + " ";
            //end while
        }
        //close scanner to avoid resource leak
        scanner.close();
        //display text in textArea2
        textArea2.setText(fullTranslation);

        //end method
    }

//end class
}

