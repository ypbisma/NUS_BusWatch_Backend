package com.buswatchbackend;

public class MacAddressLocationFloorConverter {


	
	private static final String[][] zones = {
			{"KR-ENGADM", "Engineering Admin"},
			{"KR-CCELIB", "Central Library"},
			{"KR-AKIENG", "Architecture and Engineering"},
				{"KR-FBALAW", "Bizad and Law"},
				{"KR-SCIPGP", "Science and Prince George's Park"},
					{"KR-ARTS", "Arts & Social Science"},
					{"KR-MEDNUH", "Medicine & NUH"},
						{"KR-SCIYIH", "Science Faculty & YIH"},
						{"UTown", "University Town"}};
	
	private static final String[][] ENGADM_Building = {
			{"ENGADM-E3A", "E3A"},
			{"E2A", "E2A"},
			{"ENGADM-EA", "EA"},
			{"ENGADM-E4A", "E4A"},
			{"LT7A", "LT7A"},
			{"ENGADM-E4", "E4"},
			{"ENGADM-TLAB", "TLAB"},
			{"LT7", "LT7"},
			{"LT6", "LT6"},
			{"ENGADM-UCC", "UCC"},
			{"ENGADM-E5", "E5"},
			{"ENGADM-EW1", "EW1"},
			{"ENGADM-YSTCM", "Yong Siew Toh Conservatory of Music"},
			{"ENGADM-E1A", "E1A"}};
	
	private static final String[][] CCELIB_Building = {
			{"LT3", "LT3"},
			{"CCELIB-KR12A", "KR12A"},
			{"CCELIB-Central Library", "Central Library"},
			{"CCELIB-S2S", "S2S"},
			{"CCELIB-CDTL", "Center for Development of Teaching and Learning"},
			{"CCELIB-CC", "Computer Center"},
			{"CCELIB-KR11", "KR11"}};
	
	private static final String[][] AKIENG_Building = {
			{"AKIENG-ENGINE CANTEEN", "Techno Edge Canteen"},
			{"AKIENG-SDE1", "SDE1"},
			{"AKIENG-SDE2", "SDE2"},
			{"AKIENG-SDE3", "SDE3"},
			{"AKIENG-E1", "E1"},
			{"AKIENG-E2", "E2"},
			{"AKIENG-E3", "E3"},
			{"LT1", "LT1"},
			{"LT5", "LT5"},
			{"AKIENG-EW2", "EW2"},
			{"LT2", "LT2"},
			{"AKIENG-CELC", "Center for English Language Communication"}};
	
	private static final String[][] FBALAW_Building = {
			{"FBALAW-MochtarRiyady", "Mochtar Riyadi Building"},
			{"FBALAW-Alumni", "Shaw Foundation Alumni House"},
			{"FBALAW-I3", "I3"},
			{"FBALAW-BIZ2", "BIZ2"},
			{"LT19", "LT19"},
			{"LT17", "LT17"},
			{"LT16", "LT16"},
			{"LT18", "LT18"},
			{"FBALAW-CTN", "CTN"}};
	
	private static final String[][] SCIPGP_Building = {
			{"LT32", "LT32"},
			{"SCIPGP-S9","S9"},
			{"SCIPGP-S6", "S6"},
			{"SCIPGP-S3", "S3"},
			{"SCIPGP-S1", "S1"},
			{"SCIPGP-S4", "S4"},
			{"SCIPGP-S5", "S5"},
			{"SCIPGP-S8", "S8"},
			{"SCIPGP-S4A", "S4A"},
			{"SCIPGP-S2", "S2"},
			{"LT22", "LT22"},
			{"SCIPGP-S1A", "S1A"}};
	
	private static final String[][] ARTS_Building ={
			{"ARTS-AS4", "AS4"},
			{"ARTS-AS1","AS1"},
			{"ARTS-AS7", "AS7"},
			{"ARTS-Canteen", "The Deck"},
			{"ARTS-AS5", "AS5"},
			{"ARTS-AS3", "AS3"},
			{"ARTS-AS2", "AS2"},
			{"ARTS-AS6", "AS6"}};
	
	private static final String[][] MEDNUH_Building ={
			{"MEDNUH-MD6", "MD6"},
			{"MEDNUH-CELS","Center for Life Science"},
			{"MEDNUH-FOD", "Faculty of Dentistry"},
			{"MEDNUH-MD7", "MD7"},
			{"MEDNUH-MD5", "MD5"},
			{"MEDNUH-MD1", "MD1"},
			{"MEDNUH-MD9", "MD9"},
			{"MEDNUH-MD4", "MD4"}, 
			{"MEDNUH-MD10", "MD10"},
			{"MEDNUH-MD11", "MD11"},
			{"MEDNUH-MD3", "MD3"},
			{"LT29", "LT29"},
			{"LT26", "LT26"},
			{"LT27", "LT27"},
			{"LT24", "LT24"},
			{"LT25", "LT25"},
			{"LT28", "LT28"}};
	
	private static final String[][] SCIYIH_Building = {
			{"SCIYIH-S11", "S11"},
			{"SCIYIH-S12", "S12"},
			{"SCIYIH-YIH", "Yushof Ishak House"},
			{"SCIYIH-UHALL", "University Hall"},
			{"SCIYIH-UHC", "University Health Centre"},
			{"SCIYIH-S13", "S13"},
			{"LT31", "LT31"},
			{"SCIYIH-S14", "S14"},
			{"SCIYIH-S17", "S17"},
			{"SCIYIH-S16", "S16"},
			{"SCIYIH-S7", "S7"},
			{"LT26", "LT26"},
			{"SCIYIH-S15", "S15"},
			{"LT34", "LT34"}};
	
	private static final String[][] UTown_Building = {
			{"UT01-RC4-MPH", "RC4 Multi-purpose Hall"},
			{"UT03-Dining Hall", "Dining Hall"},
			{"UTown-YNC-RC1B", "YaleNUS College RC1B"},
			{"UTown-YNC-EC", "YaleNUS Elm College"},
			{"UTown-YNC-RC1A", "YaleNUS RC1A"},
			{"UT04-CAPT", "College of Alice & Peter Tan"},
			{"UTown-Tembusu", "Tembusu College"},
			{"UT02-RC4", "RC4"},
			{"UTown-TMPH", "Tembusu Multi-purpose Hall"},
			{"UTown-Cinanmon", "Cinnamon College"},
			{"UTown-ERC", "Education Resource Centre"},
			{"UTown-CREATE", "CREATE Tower"},
			{"UTown-CMPH", "Cinnamon Multi-purpose Hall"},
			{"UTown-GRS", "Graduate Residence"},
			{"UTown-GRN", "Stephen Riady Centre"},
			{"UTown-TLL", "Town Plaza"}};

	public void convert(String floorString){
		String inputZone;
		String inputBuilding;
		String inputFloor;
		
		inputZone = floorString.split(">")[0];
		inputBuilding = floorString.split(">")[1];
		inputFloor = floorString.split(">")[2];
		
		switch(inputZone){
		case "KR-ENGADM": 
			convertZone = zones[0][1];
			convertBuilding = findBuildingCounterpart(ENGADM_Building, inputBuilding);
			break;
		case "KR-CCELIB":
			convertZone = zones[1][1];
			convertBuilding = findBuildingCounterpart(CCELIB_Building, inputBuilding);
			break;
		case "KR-AKIENG":
			convertZone = zones[2][1];
			convertBuilding = findBuildingCounterpart(AKIENG_Building, inputBuilding);
			break;
		case "KR-FBALAW":
			convertZone = zones[3][1];
			convertBuilding = findBuildingCounterpart(FBALAW_Building, inputBuilding);
			break;
		case "KR-SCIPGP":
			convertZone = zones[4][1];
			convertBuilding = findBuildingCounterpart(SCIPGP_Building, inputBuilding);
			break;
		case "KR-ARTS":
			convertZone = zones[5][1];
			convertBuilding = findBuildingCounterpart(ARTS_Building, inputBuilding);
			break;
		case "KR-MEDNUH":
			convertZone = zones[6][1];
			convertBuilding = findBuildingCounterpart(MEDNUH_Building, inputBuilding);
			break;
		case "KR-SCIYIH":
			convertZone = zones[7][1];
			convertBuilding = findBuildingCounterpart(SCIYIH_Building, inputBuilding);
			break;
		case "UTown":
			convertZone = zones[8][1];
			convertBuilding = findBuildingCounterpart(UTown_Building, inputBuilding);
			break;
		default:
			break;
		}
		
		convertFloor = inputFloor.split("-")[2];
		
	}
	
	public String findBuildingCounterpart(String[][] sourceArray, String targetElement){
		int location = 0;
		int i = 0;
		boolean found = false;
		while (!found && i < sourceArray.length){
			String floor = sourceArray[i][0];
			if(floor.equalsIgnoreCase(targetElement)){
				location = i;
				found = true;
			}
			i++;
		}
		
		return sourceArray[location][1];
	}
	
	public String findFloor(String floorString){
		String x = null;
		return x;
	}
	
	public String getConvertZone() {
		return convertZone;
	}

	public void setConvertZone(String convertZone) {
		this.convertZone = convertZone;
	}

	public String getConvertBuilding() {
		return convertBuilding;
	}

	public void setConvertBuilding(String convertBuilding) {
		this.convertBuilding = convertBuilding;
	}

	public String getConvertFloor() {
		return convertFloor;
	}

	public void setConvertFloor(String convertFloor) {
		this.convertFloor = convertFloor;
	}

	private String convertZone;
	private String convertBuilding;
	private String convertFloor;
}
