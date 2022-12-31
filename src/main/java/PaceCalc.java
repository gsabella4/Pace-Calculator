import java.text.DecimalFormat;
import java.util.Scanner;

public class PaceCalc {
    public static void main(String[] args) {

        //final variables for preloaded distance options
        final double FIVE_K = 3.1;
        final double TEN_K = 6.2;
        final double HALF_MARATHON = 13.1;
        final double MARATHON = 26.2;
        final double FIFTY_K = 31.0;
        final double FIFTY_MILER = 50.0;

        //variable for distance, pace, duration factors
        double distance, pace, paceSecond;
        int durationHour, durationMinute, durationSeconds, durationTime;
        String duration;
        int paceMinute;

        UserOutput.introDisplay();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            UserOutput.distanceMenu();

            String menuSelection = scanner.nextLine();

            if (menuSelection.equalsIgnoreCase("c")) {
                UserOutput.distancePrompt();
                try {
                    distance = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                    UserOutput.invalidDistance();
                    continue;
                }
                if (distance < 1.0 || distance > 300) {
                    UserOutput.invalidDistance();
                    continue;
                }
            }

            switch (menuSelection) {
                case "1":
                    distance = FIVE_K;
                    break;
                case "2":
                    distance = TEN_K;
                    break;
                case "3":
                    distance = HALF_MARATHON;
                    break;
                case "4":
                    distance = MARATHON;
                    break;
                case "5":
                    distance = FIFTY_K;
                    break;
                case "6":
                    distance = FIFTY_MILER;
                    break;
                default:
                    UserOutput.invalidMenuOption();
                    continue;
            }

            while (true) {
                UserOutput.durationPrompt();
                duration = scanner.nextLine();
                if (duration.length() != 8) {
                    UserOutput.invalidDuration();
                    continue;
                }

                //convert string duration into Hour, Minute and seconds as integers
                durationHour = Integer.parseInt(duration.substring(0, 2));
                durationMinute = Integer.parseInt(duration.substring(3, 5));
                durationSeconds = Integer.parseInt(duration.substring(6, 8));

                // calculate pace based on seconds
                durationTime = durationSeconds + durationMinute * 60 + durationHour * 60 * 60;
                pace = durationTime / distance;

                // convert seconds into Minutes and seconds
                paceMinute = (int) (pace / 60);
                paceSecond = pace - paceMinute * 60;

                //formatting
                DecimalFormat formatObject = new DecimalFormat("00");

                //printing to user
                if (paceSecond > 1) {
                    System.out.println("\nYour pace is (" + formatObject.format(paceMinute) + ":" + formatObject.format(paceSecond) + ") "
                            + formatObject.format(paceMinute) + " minutes and " + formatObject.format(paceSecond) + " seconds per mile");
                } else {
                    System.out.println("\nYour pace is (" + formatObject.format(paceMinute) + ":" + formatObject.format(paceSecond) + ") "
                            + formatObject.format(paceMinute) + " minutes and " + formatObject.format(paceSecond) + " second per mile");
                }

                //Option to run through another calculation or exit, anything other than Y||y will exit
                UserOutput.anotherPacePrompt();
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("Y")) {
                    break;
                } else {
                    UserOutput.goodbyeMessage();
                    System.exit(1);
                }
            }
        }
    }
}







