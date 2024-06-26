import edu.duke.*;

public class Part4 {
    public void findYouTubeLinks() {
        URLResource file = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String item : file.words()) {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if (pos != -1) {
                int beg = item.lastIndexOf("\"", pos);
                int end = item.indexOf("\"", pos + 1);
                System.out.println(item.substring(beg + 1, end));
            }
        }
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4();
        part4.findYouTubeLinks();
    }
}
