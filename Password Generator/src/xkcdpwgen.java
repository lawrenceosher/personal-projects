import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class xkcdpwgen {
  public static void main(String[] args) {

    ArrayList<String> possibleWords = new ArrayList<>();
    ArrayList<String> wordsToBePutTogether = new ArrayList<>();
    String password = "";
    int words = 4;
    int caps = 0;
    int numbers = 0;
    int symbols = 0;

    String[] availableSymbols = {"~", "!", "@", "#", "$", "%", "^", "&", "*", ".", ":", ";"};


    String helpText = "$ ./xkcdpwgen -h\n" +
            "usage: xkcdpwgen [-h] [-w WORDS] [-c CAPS] [-n NUMBERS] [-s SYMBOLS]\n" +
            "                \n" +
            "Generate a secure, memorable password using the XKCD method\n" +
            "                \n" +
            "optional arguments:\n" +
            "    -h, --help            show this help message and exit\n" +
            "    -w WORDS, --words WORDS\n" +
            "                          include WORDS words in the password (default=4)\n" +
            "    -c CAPS, --caps CAPS  capitalize the first letter of CAPS random words\n" +
            "                          (default=0)\n" +
            "    -n NUMBERS, --numbers NUMBERS\n" +
            "                          insert NUMBERS random numbers in the password\n" +
            "                          (default=0)\n" +
            "    -s SYMBOLS, --symbols SYMBOLS\n" +
            "                          insert SYMBOLS random symbols in the password\n" +
            "                          (default=0)";

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-h":
        case "--help":
          System.out.println(helpText);
          return;
        case "-w":
        case "--words":
          try {
            words = Integer.parseInt(args[i + 1]);
            i = i + 1;
            if (words < 1) {
              throw new NumberFormatException();
            }
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("There is not a valid number following " +
                    "your argument.");
          }
          break;
        case "-c":
        case "--caps":
          try {
            caps = Integer.parseInt(args[i + 1]);
            i = i + 1;
            if (caps < 1) {
              throw new NumberFormatException();
            }
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("There is not a valid number following " +
                    "your argument.");
          }
          break;
        case "-n":
        case "--numbers":
          try {
            numbers = Integer.parseInt(args[i + 1]);
            i = i + 1;
            if (numbers < 1) {
              throw new NumberFormatException();
            }
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("There is not a valid number " +
                    "following your argument.");
          }
          break;
        case "-s":
        case "--symbols":
          try {
            symbols = Integer.parseInt(args[i + 1]);
            i = i + 1;
            if (symbols < 1) {
              throw new NumberFormatException();
            }
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("There is not a valid number " +
                    "following your argument.");
          }
          break;
        default:
          throw new IllegalArgumentException("Invalid argument.");
      }
    }

    if (caps > words) {
      throw new IllegalArgumentException("Can't have more capital words than words.");
    }



    FileInputStream fileStream = null;
    try {
      fileStream = new FileInputStream("words.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    BufferedReader buff;
    String line;
    buff = new BufferedReader(new InputStreamReader(fileStream, StandardCharsets.UTF_8));
    try {
      while ((line = buff.readLine()) != null) {
        possibleWords.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


    Random rand = new Random();

    for (int i = 0; i < words; i = i + 1) {
      wordsToBePutTogether.add(possibleWords.get(rand.nextInt(400000)).toLowerCase());
    }

    for (int a = 0; a < caps; a = a + 1) {
      String word = wordsToBePutTogether.get(a);
      String capWord = word.toUpperCase();
      char firstLetter = capWord.charAt(0);
      String restOfWord = word.substring(1);
      String first = Character.toString(firstLetter);
      word = first.concat(restOfWord);
      wordsToBePutTogether.set(a, word);
    }

    for (int b = 0; b < numbers; b = b + 1) {
      int spotForNum = rand.nextInt(2);
      int randNum = rand.nextInt(100);
      int randIndx = rand.nextInt(wordsToBePutTogether.size());
      String word = wordsToBePutTogether.get(randIndx);

      //prepend
      if (spotForNum == 0) {
        word = Integer.toString(randNum).concat(word);
      }

      //append
      if (spotForNum == 1) {
        word = word.concat(Integer.toString(randNum));
      }
      wordsToBePutTogether.set(randIndx, word);
    }

    for (int c = 0; c < symbols; c = c + 1) {
      int spotForNum = rand.nextInt(2);
      int randNum = rand.nextInt(availableSymbols.length);
      String addedSymbol = availableSymbols[randNum];
      int randIndx = rand.nextInt(wordsToBePutTogether.size());
      String word = wordsToBePutTogether.get(randIndx);

      //prepend
      if (spotForNum == 0) {
        word = addedSymbol.concat(word);
      }

      //append
      if (spotForNum == 1) {
        word = word.concat(addedSymbol);
      }

      wordsToBePutTogether.set(randIndx, word);
    }

    Collections.shuffle(wordsToBePutTogether);

    for (int d = 0; d < wordsToBePutTogether.size(); d = d + 1) {

      password = password + wordsToBePutTogether.get(d);

    }

    System.out.println(password);

  }
}
