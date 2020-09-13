package resource;

public class Acronyms {


	
	private static String[] acr ={"American Broadcasting Company","American Chemical Society","Allgemeine Elektrische Gesellschaft Telefunken","Atomic Energy Research Establishment","American Federation of Labor","Congress of Industrial Organizations","Agricultural and Food Research Council","Agronne National Laboratory","American Society for Testing and Materials","Association for Computing Machinery","Bad Anilin and Soda Fabrik AG","Brookhaven National Laboratory","Civil Aeronautics Board","California Institute of Technology","Columbia Broadcasting System","Centers for Disease Control","Centers for Disease Control and Prevention","Commission Energie Atomique","Centre Etudes Nucleaires","Centre Etudes Nucleaires Cadarache","Centre Etudes Nucleaires Grenoble","Centre Etudes Nucleaires Studiecentrum Kernenergie","Centre Etudes Nucleaires Fontenay aux Roses","Centre Etudes Nucleaires Saclay","Centre Etudes Recherche Nucleaires","Centre Hospitalier Regional","Centre Hospitalier Universitaire","Comitato Nazionale Energia Nucleare","Consiglio Nazionale Ricerche","Centre Nazionale Recherche Scientifique","Consejo Superior Investigaciones Cientificas","Council of Scientific and Industrial Research","Commonwealth Scientific and Industrial Research Organization","City University of New York","Deutsche Elektronen Synchrotron","Deutsche Forschung & Versuchanstalt Luft & Raumfahrt EV","Department of Scientific and Industrial Research","E.I. Dupont de Nemours","Equipe Recherche","Equipe Recherche Associe","Eidgenossische Technische Hochschule","European Atomic Energy Community","Federal Aviation Agency","Food and Agriculture Organization","Federal Communications Commission","Stichting Fundamenteel Onderzoek Materie","Formation Recherche Associe","General Electric Company","General Motors Corporation","International Business Machines Corporation","Imperial Chemical Industries PLC","Illinois Institute of Technology","Institute for Atomic Energy","Institute of Automobile Engineer","Institute of Electrical and Electronic Engineers","Institute of Electronic Engineering","Institute National Recherche Agronomique","Institute National Sante and Recherche Medicale","International Telephone and Telegraph Corporation","Kernforschung Anlage Julich GMBH","Ministry of Agriculture, Fisheries and Food","Minnesota Mining and Manufacturing Company","Massachusetts Institute of Technology","Medical Research Council","National Aeronautics and Space Administration","National Broadcasting Company","Natural Environment Research Council","National Institutes of Health","National Cancer Institute","National Eye Institute","National Heart Lung and Blood Institute","National Institute Allergy and Infectious Diseases","National Institute Arthritis Metabolism and Digestive Diseases","National Institute Child Health and Human Development","National Institute Dental Research","National Institute Mental Health","National Institute Neurological & Communicative Disorders & Strokes","National Oceanic and Atmospheric Administration","Norges Tekniske Hogskole","New York University","Oak Ridge National Laboratory","Office Recherche Scientifique and Technologique Outre Mer","Public Health Service","Royal Air Force","Radio Corporation America","Science and Engineering Research Council","Smith Kline and French","Skf Ball Bearings","Society of Automotive Engineers","Society of Photo-Optical Instrumentation Engineers","State University of New York","Toegepast Natuurwetenschappelijk Onderzoek","Unite Enseignement and Recherche","United Kingdom Atomic Energy Authority","United Nations","UN Educational Scientific and Cultural Organization","US Department of Health Education and Welfare","US Department of Health and Human Services","US Department of Energy","US Environmental Protection Agency","US Energy Research and Development Administration","US Food and Drug Administration","US Public Health Service","US Army","US Air Force","US Department of Agriculture","US Department of Agriculture Agricultural Research Service","US Department of Agriculture Science and Education Administration","US Army","US Air Force","University of Wales Institute of Science and Technology","World Health Organization"};
	private static String[] acr_term ={"ABC","ACS","AEG TELEFUNKEN","AERE","AFL","CIO","AFRC","ANL","ASTM","ACM","BASF AG","BNL","CAB","CALTECH","CBS","CDC","CDCP","CEA","CEN","CEN CADARACHE","GEN GRENOBLE","CEN SCK","CENFAR","CENS","CERN","CHR","CHU","CNEN","CNR","CNRS","CSIC","CSIR","CSIRO","CUNY","DESY","DFVLR","DSIR","DUPONT CO","ER","ERA","ETH","EURATOM","FAA","FAO","FCC","FOM","FRA","GE","GM CORP","IBM CORP","ICI PLC","IIT","IAE","IAE","IEEE","IEE","INRA","INSERM","ITT CORP","KFA JULICH GMBH","MAFF","3M CO","MIT","MRC","NASA","NBC","NERC","NIH","NCI","NEI","NHLBI","NIAID","NIAMDD","NICHHD","NIDR","NIMH","NINCDS","NOAA","NTH","NYU","ORNL","ORSTOM","PHS","RAF","RCA CORP","SERC","SK&F","SKF","SAE","SPIE","SUNY","TNO","UER","UKAEA","UN","UNESCO","US DEPT HEW","US DEPT HHS","US DOE","US EPA","US ERDA","US FDA","US PHS","USA","USAF","USDA","USDA ARS","USDA SEA","USA","USAF","UWIST","WHO"};
	
	
	public static String Acronym(String s){
		
		String s1 = s.replace(",", "");
		
		for(int i=0;i<acr.length;i++){
		
			s1=s1.replace(acr[i], acr_term[i]);
			
		}
			
	return s1;	
	}
		
}


