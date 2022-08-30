package plants;

//setting this up as the intent describes
//member values are protected, the only other thing this class
//does is to check intended setter parameters for reasonableness
public class Plant {
	protected String mCommonName;
	protected String mScientificName;
	protected String mTypeOfPlant;

	protected int mMinimumGrowingZone;
	protected int mMaximumGrowingZone;
	
	protected final static int MAXIMUM_HARDINESS_ZONE = 13;
	protected final static int MINIMUM_HARDINESS_ZONE = 1;
	
	//getters and setters for common name
	public String getCommonName() {
		return mCommonName;
	}
	
	public void setCommonName(String commonName) {
		this.mCommonName = commonName;
	}

	//getters and setters for scientific name
	public String getScientificName() {
		return mScientificName;
	}

	public void setScientificName(String scientificName) {
		this.mScientificName = scientificName;
	}

	//getters and setters for type of plant
	public String getTypeOfPlant() {
		return mTypeOfPlant;
	}

	public void setTypeOfPlant(String typeOfPlant) {
		this.mTypeOfPlant = typeOfPlant;
	}
	
	//getters and setters for minimum growing zone
	public int getMinimumGrowingZone() {
		return mMinimumGrowingZone;
	}
	
	public void setMinimumGrowingZone(int minimumGrowingZone) throws IllegalArgumentException {
		if (!isGrowingZoneAcceptable(minimumGrowingZone))
			throw new IllegalArgumentException("exceeds minimum hardiness value");
		this.mMinimumGrowingZone = minimumGrowingZone;
	}
	
	//getters and setters for maximum growing zone
	public int getMaximumGrowingZone() {
		return mMaximumGrowingZone;
	}
	
	public void setMaximumGrowingZone(int maximumGrowingZone) throws IllegalArgumentException {
		if (!isGrowingZoneAcceptable(maximumGrowingZone))
			throw new IllegalArgumentException("exceeds maximum hardiness value");
		this.mMaximumGrowingZone = maximumGrowingZone;
	}
	
	//utility function to check whether a zone is between this plants bounds
	protected boolean isGrowingZoneAcceptable(int growingZone) {
		if ((growingZone > MAXIMUM_HARDINESS_ZONE) || (growingZone < MINIMUM_HARDINESS_ZONE)) 
			return false;
		else
			return true;
	}
}
