import java.util.Arrays;

public class WordGram {
    private String[] myWords;
    private int myHash; // Cached hash code

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        myHash = Arrays.hashCode(myWords); // Initialize the cached hash code
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }

    public int length() {
        return myWords.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : myWords) {
            sb.append(word).append(" ");
        }
        return sb.toString().trim(); // Remove the trailing space
    }

    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof WordGram)) return false; // Correct type checking
    WordGram other = (WordGram) o;
    return Arrays.equals(myWords, other.myWords);
}

    @Override
    public int hashCode() {
        return myHash; // Return the cached hash code
    }

    public WordGram shiftAdd(String word) {
        WordGram out = new WordGram(myWords, 0, myWords.length);
        if (word == null) {
            word = "";
        }

        System.arraycopy(out.myWords, 1, out.myWords, 0, out.myWords.length - 1);
        out.myWords[out.myWords.length - 1] = word;
        out.myHash = Arrays.hashCode(out.myWords); // Update the cached hash code
        return out;
    }
}
