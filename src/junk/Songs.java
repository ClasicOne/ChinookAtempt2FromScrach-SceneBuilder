package junk;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Songs {
	private final StringProperty name, album, mediaType, genre, composer;
	private final FloatProperty price;

	public Songs(String name, String album, String mediaType, String genre, String composer, float price) {
		this.name = new SimpleStringProperty(name);
		this.album = new SimpleStringProperty(album);
		this.mediaType = new SimpleStringProperty(mediaType);
		this.genre = new SimpleStringProperty(genre);
		this.composer = new SimpleStringProperty(composer);
		this.price = new SimpleFloatProperty(price);
	}
	public String getName(){
		return name.get();
	}
	public String getMediaType(){
		return mediaType.get();
	}
	public String getGenre(){
		return genre.get();
	}
	public String getComposer(){
		return composer.get();
	}
	public String getAlbum(){
		return album.get();
	}
	public Float getPrice(){
		return price.get();
	}
}