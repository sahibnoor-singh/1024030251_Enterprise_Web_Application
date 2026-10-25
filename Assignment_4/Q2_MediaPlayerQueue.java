// Q2 - Media player queue using LinkedList and ListIterator
import java.util.*;

class Song {
    String title;
    String artist;

    Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Song)) return false;
        Song s = (Song) o;
        return title.equalsIgnoreCase(s.title) && artist.equalsIgnoreCase(s.artist);
    }

    public int hashCode() {
        return Objects.hash(title.toLowerCase(), artist.toLowerCase());
    }

    public String toString() {
        return title + " - " + artist;
    }
}

public class Q2_MediaPlayerQueue {

    static LinkedList<Song> queue = new LinkedList<>();
    static LinkedList<Song> history = new LinkedList<>();
    static ListIterator<Song> cursor = queue.listIterator();
    static boolean movingForward = true;

    static void resetCursor() {
        cursor = queue.listIterator();
        movingForward = true;
    }

    static void addSong(Song s) {
        queue.addLast(s);
        resetCursor();
    }

    // priority
    static void addToFront(Song s) {
        queue.addFirst(s);
        resetCursor();
    }

    static void insertAt(int pos, Song s) {
        ListIterator<Song> it = queue.listIterator(pos);
        it.add(s);
        resetCursor();
    }

    static int removeDuplicates() {
        HashSet<Song> seen = new HashSet<>();
        int count = 0;
        Iterator<Song> it = queue.iterator();
        while (it.hasNext()) {
            if (!seen.add(it.next())) {
                it.remove();
                count++;
            }
        }
        resetCursor();
        return count;
    }

    static void addToHistory(Song s) {
        history.remove(s);
        history.addFirst(s);
        if (history.size() > 5) {
            history.removeLast();
        }
    }

    static Song playNext() {
        // skip the song we just went back over
        if (!movingForward && cursor.hasNext()) {
            cursor.next();
        }
        if (!cursor.hasNext()) {
            movingForward = true;
            return null;
        }
        Song s = cursor.next();
        movingForward = true;
        addToHistory(s);
        return s;
    }

    static Song playPrevious() {
        if (movingForward && cursor.hasPrevious()) {
            cursor.previous();
        }
        if (!cursor.hasPrevious()) {
            movingForward = false;
            return null;
        }
        Song s = cursor.previous();
        movingForward = false;
        addToHistory(s);
        return s;
    }

    static void printForward() {
        ListIterator<Song> it = queue.listIterator();
        int i = 1;
        while (it.hasNext()) {
            System.out.println(i++ + ". " + it.next());
        }
    }

    static void printBackward() {
        ListIterator<Song> it = queue.listIterator(queue.size());
        int i = queue.size();
        while (it.hasPrevious()) {
            System.out.println(i-- + ". " + it.previous());
        }
    }

    public static void main(String[] args) {
        addSong(new Song("Song A", "Artist 1"));
        addSong(new Song("Song B", "Artist 2"));
        addSong(new Song("Song C", "Artist 3"));
        addSong(new Song("Song B", "Artist 2"));
        addSong(new Song("Song D", "Artist 4"));
        addSong(new Song("song a", "artist 1"));

        System.out.println("--- Queue ---");
        printForward();

        System.out.println("\n--- Add to front ---");
        addToFront(new Song("Hit Single", "Star"));
        printForward();

        System.out.println("\n--- Insert at 3 ---");
        insertAt(3, new Song("Inserted Track", "Guest"));
        printForward();

        System.out.println("\n--- Remove duplicates ---");
        System.out.println("Removed: " + removeDuplicates());
        printForward();

        System.out.println("\n--- Backward ---");
        printBackward();

        System.out.println("\n--- Playing ---");
        System.out.println("next: " + playNext());
        System.out.println("next: " + playNext());
        System.out.println("next: " + playNext());
        System.out.println("prev: " + playPrevious());
        System.out.println("prev: " + playPrevious());
        System.out.println("next: " + playNext());

        System.out.println("\nHistory: " + history);
    }
}
