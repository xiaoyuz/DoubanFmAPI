package domain;

import java.util.ArrayList;
import java.util.List;

import data.Song;

public class PlayListResult extends AbstractResult {

	protected String r;
	protected String is_show_quick_start;
	protected List<Song> song = new ArrayList<Song>();

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getIs_show_quick_start() {
		return is_show_quick_start;
	}

	public void setIs_show_quick_start(String is_show_quick_start) {
		this.is_show_quick_start = is_show_quick_start;
	}

	public List<Song> getSong() {
		return song;
	}

	public void setSong(List<Song> song) {
		this.song = song;
	}

}
