package exception;

@SuppressWarnings("serial")
public class LevelIsNotValid extends Exception {

	public LevelIsNotValid(String message) {
		super(message);
	}

	public LevelIsNotValid() {
		super();
	}
}
