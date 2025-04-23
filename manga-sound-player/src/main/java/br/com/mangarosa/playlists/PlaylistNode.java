package br.com.mangarosa.playlists;

public class PlaylistNode {
    private Music music;
    private PlaylistNode next;
    private PlaylistNode prev;

    public PlaylistNode(Music music) {
        this.music = music;
    }

    public Music getMusic() {
        return music;
    }

    public PlaylistNode getNext() {
        return next;
    }

    public void setNext(PlaylistNode next) {
        this.next = next;
    }

    public PlaylistNode getPrev() {
        return prev;
    }

    public void setPrev(PlaylistNode prev) {
        this.prev = prev;
    }
}
