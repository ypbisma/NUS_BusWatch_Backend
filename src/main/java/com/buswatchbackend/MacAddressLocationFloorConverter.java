package com.buswatchbackend;

public class MacAddressLocationFloorConverter {
	private String inputZone;
	private String building;
	private String floor;
	
	private static final String[][] zones = {
			{"KR-ENGADM", "Engineering Admin"},
			{"KR-CCELIB", "Central Library"},
			{"KR-AKIENG", "Architecture/Engineering"},
				{"KR-FBALAW", "Bizad/Law"},
				{"KR-SCIPGP", "Science and Prince George's Park"},
					{"KR-ARTS", "Arts & Social Science"},
					{"KR-MEDNUH", "Medicine & NUH"},
						{"KR-SCIYIH", "Science Faculty & YIH"},
						{"UTown", "University Town"}};
	
	private static final String[] ENGADM_Building = {
	                                     "ENGADM-E3A",
	                                     "E2A",
	                                     "ENGADM-EA",
	                                     "ENGADM-E4A",
	                                     "LT7A",
	                                     "ENGADM-E4",
	                                     "ENGADM-TLAB",
	                                     "LT7",
	                                     "LT6",
	                                     "ENGADM-UCC",
	                                     "ENGADM-E5",
	                                     "ENGADM-EW1",
	                                     "ENGADM-YSTCM",
	                                     "ENGADM-E1A"};
	
	private static final String[] CCELIB_Building = {
			"LT3",
			  "CCELIB-KR12A",
			  "CCELIB-Central Library",
			  "CCELIB-S2S",
			  "CCELIB-CDTL",
			  "CCELIB-CC",
			  "CCELIB-KR11"};
	
	private static final String[] AKIENG_Building = {
			"AKIENG-ENGINE CANTEEN",
			  "AKIENG-SDE1",
			  "AKIENG-SDE2",
			  "AKIENG-SDE3",
			  "AKIENG-E1",
			  "AKIENG-E2",
			  "AKIENG-E3",
			  "LT1",
			  "LT5",
			  "AKIENG-EW2",
			  "LT2",
			  "AKIENG-CELC"};
	
	private static final String[] FBALAW_Building = {
			"FBALAW-MochtarRiyady",
			  "FBALAW-Alumni",
			  "FBALAW-I3",
			  "FBALAW-BIZ2",
			  "LT19",
			  "LT17",
			  "LT16",
			  "LT18",
			  "FBALAW-CTN"};
	
	private static final String[] SCIPGP_Building = {
			"LT32",
			  "SCIPGP-S9",
			  "SCIPGP-S6",
			  "SCIPGP-S3",
			  "SCIPGP-S1",
			  "SCIPGP-S4",
			  "SCIPGP-S5",
			  "SCIPGP-S8",
			  "SCIPGP-S4A",
			  "SCIPGP-S2",
			  "LT22",
			  "SCIPGP-S1A"};
	
	private static final String[] ARTS_Building ={
			"ARTS-AS4",
			  "ARTS-AS1",
			  "ARTS-AS7",
			  "ARTS-Canteen",
			  "ARTS-AS5",
			  "ARTS-AS3",
			  "ARTS-AS2",
			  "ARTS-AS6"};
	
	private static final String[] MEDNUH_Building ={
			"MEDNUH-MD6",
			  "MEDNUH-CELS",
			  "MEDNUH-FOD",
			  "MEDNUH-MD7",
			  "MEDNUH-MD5",
			  "MEDNUH-MD1",
			  "MEDNUH-MD9",
			  "MEDNUH-MD4",
			  "MEDNUH-MD10",
			  "MEDNUH-MD11",
			  "MEDNUH-MD3",
			  "LT29",
			  "LT26",
			  "LT27",
			  "LT24",
			  "LT25",
			  "LT28"};
	
	private static final String[] SCIYIH_Building = {
			 "SCIYIH-S11",
			  "SCIYIH-S12",
			  "SCIYIH-YIH",
			  "SCIYIH-UHALL",
			  "SCIYIH-UHC",
			  "SCIYIH-S13",
			  "LT31",
			  "SCIYIH-S14",
			  "SCIYIH-S17",
			  "SCIYIH-S16",
			  "SCIYIH-S7",
			  "LT26",
			  "SCIYIH-S15",
			  "LT34"};
	
	private static final String[] UTown_Building = {
			"UT01-RC4-MPH",
			  "UT03-Dining Hall",
			  "UTown-YNC-RC1B",
			  "UTown-YNC-EC",
			  "UTown-YNC-RC1A",
			  "UT04-CAPT",
			  "UTown-Tembusu",
			  "UT02-RC4",
			  "UTown-TMPH",
			  "UTown-Cinanmon",
			  "UTown-ERC",
			  "UTown-CREATE",
			  "UTown-CMPH",
			  "UTown-GRS",
			  "UTown-GRN",
			  "UTown-TLL"};
	
	
	
	
	
	public String convert(String floorString){
		inputZone = floorString.split(">")[0];
		building = floorString.split(">")[1];
		floor = floorString.split(">")[2];
		
		
		return inputZone + "x" + building + "x" + floor;
	}
	
//	public String convertBuilding(String building){
//		switch(building){
//		case 
		
		
		}
		
}
