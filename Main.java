import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String mainWord = "قلمدان";
        ArrayList<String> foundWords = new ArrayList<>();
        int score = 0;
        int level = 1;
        String[] dictLevel1 = {
                "قلم", "دان", "دل", "مدل", "نمد", "دال", "قال"
        };
        String[] dictLevel2 = {
                "کتاب", "خانه", "تاب", "خان", "ناب", "بانک"
        };
        String[] dictLevel3 = {
                "دانش", "شاذ", "دندان", "شار", "شد"
        };
        System.out.println("🎮 به بازی کلمه سازی خوش آمدید");
        System.out.println("با این کلمه ,کلمات جدیدی بساز: " + mainWord);
        System.out.println("برای خروج Exit را تایپ کنید");
        while (true) {
            System.out.print("your word: ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("EXIT")) {
                System.out.println("بازی تمام شد!");
                System.out.println("your score: " + score);
                break;
            }
            if (score >= 400) {
                System.out.println("\n🎉 GAME OVER - YOU WON!");
                System.out.println("final score: " + score);
                break;
            }
            if (input.length() < 3) {
                System.out.println("❌ کلمه خیلی کوتاهه!");
                continue;
            }
            if (foundWords.contains(input)) {
                System.out.println("❌ کلمه تکراریه");
                continue;
            }
            String[] currentDict;
            if (level == 1) {
                currentDict = dictLevel1;
            } else if (level == 2) {
                currentDict = dictLevel2;
            } else {
                currentDict = dictLevel3;
            }
            if (!isMeaningful(input, currentDict)) {
                System.out.println("❌ این کلمه معنی‌دار نیست یا اشتباهه!");
                continue;
            }
            if (isValidWord(input, mainWord)) {
                foundWords.add(input);
                score += input.length() * 10;
                System.out.println("✔ عالی! امتیاز شما: " + score);
            } else {
                System.out.println("❌ حروف کلمه مجاز نیست");
            }
            if (score >= 160 && level == 1) {
                level = 2;
                foundWords.clear();
                mainWord = "کتابخانه";
                System.out.println("\n🎉 تبریک! رفتی مرحله 2");
                System.out.println("کلمه جدید: " + mainWord);
            }
            if (score >= 340 && level == 2) {
                level = 3;
                foundWords.clear();
                mainWord = "دانشمند";
                System.out.println("\n🎉 تبریک! رفتی مرحله 3");
                System.out.println("کلمه جدید: " + mainWord);
            }
        }
        scanner.close();
    }
    static boolean isMeaningful(String word, String[] dictionary) {
        for (String w : dictionary) {
            if (w.equals(word)) {
                return true;
            }
        }
        return false;
    }
    static boolean isValidWord(String word, String mainWord) {
        String temp = mainWord;
        for (char c : word.toCharArray()) {
            int index = temp.indexOf(c);
            if (index == -1) {
                return false;
            }
            temp = temp.substring(0, index) + temp.substring(index + 1);
        }
        return true;
    }
}
