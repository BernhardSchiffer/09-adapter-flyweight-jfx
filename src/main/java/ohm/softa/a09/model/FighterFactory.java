package ohm.softa.a09.model;

import ohm.softa.a09.model.empire.TieBomber;
import ohm.softa.a09.model.empire.TieFighter;
import ohm.softa.a09.model.empire.TieInterceptor;
import ohm.softa.a09.model.rebellion.AWing;
import ohm.softa.a09.model.rebellion.XWing;
import ohm.softa.a09.model.rebellion.YWing;
import ohm.softa.a09.resource.FxImageLoaderAdapter;
import ohm.softa.a09.resource.ResourceLoader;
import ohm.softa.a09.util.NameGenerator;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Factory to create new fighters
 * Creates random fighters
 *
 * @author Peter Kurfer
 */
public final class FighterFactory {

	private static final int NumberOfKnownFighterTypes = 6;
	private final Random random;
	private final NameGenerator nameGenerator;
	private final FxImageLoaderAdapter imageResourceLoader;
	private static final Map<String, Image> fighterImages = new HashMap<>();

	public FighterFactory() {
		nameGenerator = new NameGenerator();
		random = new Random();
		imageResourceLoader = new FxImageLoaderAdapter();
	}

	/**
	 * Create a random Fighter
	 *
	 * @implNote the method has an implementation flaw because it always loads the fighters image
	 * @return a new Fighter instance
	 */
	public Fighter createFighter() {

		switch (random.nextInt(NumberOfKnownFighterTypes)) {
			case 0:
				return new AWing(nameGenerator.generateName(), getFighterImage("fighter/awing.jpg"));
			case 1:
				return new XWing(nameGenerator.generateName(), getFighterImage("fighter/xwing.jpg"));
			case 2:
				return new YWing(nameGenerator.generateName(), getFighterImage("fighter/ywing.jpg"));
			case 3:
				return new TieBomber(nameGenerator.generateName(), getFighterImage("fighter/tiebomber.jpg"));
			case 4:
				return new TieFighter(nameGenerator.generateName(), getFighterImage("fighter/tiefighter.jpg"));
			default:
				return new TieInterceptor(nameGenerator.generateName(), getFighterImage("fighter/tieinterceptor.jpg"));
		}
	}

	private Image getFighterImage(String path) {
		if(fighterImages.containsKey(path))
			return fighterImages.get(path);

		Image image = imageResourceLoader.loadImage(path);
		fighterImages.put(path, image);
		return image;
	}
}
