package helper;



import static helper.Actions.switchTab;
//import static helper.siteName;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Actions {
	public static WebDriver driver;
	public static String siteName = "";
	static String Makes[] = { "RENT DEMOLITION", "MASSEY FERGUSON", "CRANE FRUEHAUF", "SEDDON ATKINSON",
			"IFOR WILLIAMS", "INGERSOLL RAND", "BENFORD TEREX", "IFOR WILLIAMS", "MERCEDES-BENZ", "WEIGHTLIFTER",
			"ohnUNGHEINRICH", "CATERPILLAR", "NEW HOLLAND", "DENNIS EAGLE", "GRAY & ADAMS", "ATLAS COPCO",
			"CATERPILLAR", "NEW HOLLAND", "DOWDESWELL", "JOHN DEERE", "KONGSKILDE", "LAND ROVER", "CARTWRIGHT",
			"FAYMONVILLE", "LEYLAND DAF", "OVERLANDER", "MITSUBISHI", "ARCTIC CAT", "KVERNELAND", "MCCORMICK",
			"VADERSTAD", "LAND ROVER", "MITSUBISHI", "MONTRACON", "FG WILSON", "LIEBHERR", "TAKEUCHI", "THWAITES",
			"PARMITER", "POTTINGER", "JOHNSTON", "NOOTEBOOM", "WHEELBASE", "BENFORD", "HITACHI", "HYDROMIX", "HYUNDAI",
			"KOMATSU", "MANITOU", "COUSINS", "HYUNDAI", "MARSHALL", "MCCONNEL", "POLARIS", "CHEREAU", "DENNISON",
			"DON-BUR", "FRUEHAUF", "KEL-BERG", "RENAULT", "ROTHDEAN", "BARFORD", "COMPAIR", "CUMMINS", "FINGER",
			"KOBELCO", "NISSAN", "PERKINS", "YANMAR", "AMAZONE", "BOMFORD", "FLEMING", "MANITOU", "MCHALE", "RANSOME",
			"TEAGLE", "WESSEX", "ANDOVER", "BEDFORD", "CLAYTON", "DENNIS", "SCANIA", "SCARAB", "SCHMITZ", "TERBERG",
			"TRAILER", "WILCOX", "WILSON", "AMMANN", "BOBCAT", "BOMAG", "DOOSAN", "FLEXI", "GEITH", "GENMAC", "HYSTER",
			"KALMAR", "KUBOTA", "LINDE", "MERLO", "TOYOTA", "BROWNS", "CLAAS", "FENDT", "HAYTER", "KUBOTA", "LOGIC",
			"VICON", "FODEN", "IVECO", "KRONE", "LEGRAS", "M & G", "TASKER", "VOLVO", "BENDI", "CASE", "STILL", "TEREX",
			"VOLVO", "YALE", "DEUTZ", "FORD", "HONDA", "ISEKI", "KUHN", "MAJOR", "SIMBA", "SISIS", "TWOSE", "FORD",
			"HINO", "ISUZU", "KING", "Vandenberg-Bercomex", "Central Fabricators", "American Pulverizer",
			"Mastercraft Welding", "Stewart & Stevenson", "Diamond Attachments", "Ford / New Holland",
			"Ghedini Ing. Fabio", "Gheysen & Verpoort", "Ackermann-Fruehauf", "Atlas Copco Wagner",
			"Ag Spray Equipment", "Brown Brontosaurus", "Neuson Wielsdumper", "Cabine Scania R144",
			"Kaelble - Gmeinder", "Chicago Pneumatic", "Schmitz Cargobull", "Bondioli & Pavesi", "Bolinder-Munktell",
			"Emiliana Serbatoi", "Kverneland Accord", "Kverneland Taarup", "Taarup Kverneland", "Ribouleau Monosem",
			"Briggs & Stratton", "Fanyesedékzúzó Új", "Reliance Kreifeld", "Mueller Mitteltal", "Concept Engineers",
			"Grisnich Campfens", "Stel Släntskopa /", "Astec Underground", "American LaFrance", "United Farm Tools",
			"Modele De Chariot", "Neuson Wieldumper", "Kubota Kraan 1.9T", "Välservade Airman", "Skals Rullerenser",
			"France Elevateur", "Hitachi Sumitomo", "Richter & Müller", "Rivierre-Casalis", "Müller-Mitteltal",
			"Concept Perugini", "Jansen & Heuning", "Peterson Pacific", "Genkinger-Hubtex", "Simulta Junkkari",
			"Sullivan Palatek", "Concept Products", "Eagle Iron Works", "General Electric", "Allianz-Johnston",
			"Iron City Supply", "Perunannostokone", "Aveling Barford", "Massey Ferguson", "Gregoire-Besson",
			"Platform Basket", "Precision Husky", "General Trailer", "Honkajoki Works", "Harley-Davidson",
			"Burgonyakiszedő", "Antonio Carraro", "Ebbs & Radinger", "Nfp-Eurotrailer", "Jenbacher Werke",
			"Kotschenreuther", "Herman Svendsen", "Richard Western", "Royal Greenland", "Lister - Petter",
			"Battioni-Pagani", "Symons-Nordberg", "Sa Truck Bodies", "Roopack Spimaro", "Van den Beucken",
			"American Augers", "Riviera Casalis", "Canada Trailers", "Mathews Company", "Gunnebo Johnson",
			"Fiskars Agrosta", "Kubota Kraan 5T", "Engine DAF 95XF", "Metso Nordberg", "Ingersoll Rand",
			"Gardner-Denver", "Goodwin Barsby", "PB Lifttechnik", "Allis-Chalmers", "Becker & Söhne", "Boart Longyear",
			"Mantovanibenne", "Sampo-Rosenlew", "AP Machinebouw", "Robuste Kaiser", "Ruston Bucyrus", "Steinbock Boss",
			"EURO-Jabelmann", "Küpper-Weisser", "Green Machines", "Roland Tankbau", "Bolzoni Auramo", "Cordes-Sottorf",
			"Fratelli Pezza", "Doublet-Record", "Sperry/vickers", "Clark Michigan", "Fab. Mortensen", "Brand-erbisorf",
			"Conveyor Sales", "Clever Cleaner", "Merrick Loggin", "Track Marshall", "Euclid-Hitachi", "VDL Jonckheere",
			"Screen Machine", "Gianni Ferrari", "Linden Comansa", "Auramo Bolzoni", "Bilotserkivmaz", "Austin-Western",
			"Superior Broom", "Asphalt Zipper", "National Crane", "Therm Dynamics", "Terex Woodsman", "Energy WeldFab",
			"DB ENGINEERING", "Airman-Hitachi", "Digging Bucket", "MERCEDES ATEGO", "Cabine Man TGA", "KIPPIPERÄVAUNU",
			"SCRUBBER COMAC", "Pilot Crushtec", "Kramer-allrad", "Mercedes-Benz", "Barber-Greene", "Terex Schaeff",
			"Daewoo Doosan", "Ifor Williams", "Wacker Neuson", "International", "Sauer Danfoss", "Rubble Master",
			"Trencor Jetco", "Schwarzmüller", "Valtra Valmet", "Mini Bruunett", "Cangini Benne", "Chargeur Plus",
			"Magirus Deutz", "Ammann-Yanmar", "van Lengerich", "Terex Bendini", "Hatzenbichler", "Nordhydraulic",
			"Hewitt Robins", "Terex Comedil", "Nippon Sharyo", "Ashok Leyland", "Berminghammer", "Sichelschmidt",
			"Falcon Spider", "GHH Fahrzeuge", "Valtra/valmet", "Hillspridaren", "Eagle Crusher", "Valmet/valtra",
			"Weldco Beales", "Horst Welding", "China Eastern", "Dixie Chopper", "Hollingsworth", "Maskin Mekano",
			"MERCEDES BENZ", "Forss-Parator", "Terex Finlay", "Fiat-Hitachi", "Holland Lift", "Terex Pegson",
			"Case Poclain", "Fiat-Kobelco", "JCB Vibromax", "Vogel & Noot", "Souchu Pinet", "Farmi Forest",
			"Benninghoven", "Merlin Gerin", "Auger Torque", "Bucyrus-Erie", "Italmacchine", "Gray & Adams",
			"Sobernheimer", "Binderberger", "Ala-talkkari", "Gurney Reeve", "Intermerkato", "Great Plains",
			"Big Dutchman", "Westinghouse", "Jungheinrich", "Lilla Harrie", "Schuitemaker", "Interconsult",
			"Freightliner", "Zakkenheffer", "Agro-Factory", "Agromehanika", "Dutch Dragon", "Indespension",
			"Henkelhausen", "Dala-Klippen", "Tiki Treiler", "Dronningborg", "Pompfontijne", "Schlumberger",
			"DM Machinery", "Super Prefer", "Charterhouse", "Mjölbysläpet", "Tigercrusher", "Top Trailers",
			"Western Star", "Gekko Inline", "Dyck Welding", "Aisle-Master", "Spectra-phys", "Terra Select",
			"Castelgarden", "Stalowa Wola", "﻿Continental", "Little Giant", "Naaktgeboren", "Van der Poel",
			"Daimler-Benz", "Eimco Elecon", "Blue Diamond", "Power Curber", "Hogg & Davis", "Allmand Bros",
			"Armstrong Ag", "Forest River", "Rock Systems", "Eager Beaver", "Silver Eagle", "Straightline",
			"Arrow-Master", "United Forms", "Craventasker", "Down 2 Earth", "Chelyabinets", "Engine Atego",
			"Klapisirkeli", "Caterpillar", "New Holland", "Atlas Copco", "Brown Lenox", "Faun-Frisch", "Putzmeister",
			"Zettelmeyer", "Bonfiglioli", "David Brown", "Ditch Witch", "Tadano Faun", "Metalovouga", "Lecitrailer",
			"Clark-Hurth", "Terex Demag", "Lamborghini", "Deguillaume", "Bridgestone", "Terex Fuchs", "Borg Warner",
			"Rolls Royce", "Gorman-Rupp", "PowerScreen", "Eurocrusher", "Neuenhauser", "Green Power", "Rotochopper",
			"Oil & Steel", "Terex Genie", "Continental", "Winkelbauer", "Shuttlelift", "Star Hammer", "Niskakangas",
			"Leroy Somer", "Fortschritt", "Trexcavator", "Scanclimber", "Groenewegen", "Thermo King", "Pilkemaster",
			"Hakki Pilke", "Mottimaster", "Maisonneuve", "Lintrailers", "Sulky-Burel", "Lancer Boss", "Faymonville",
			"Dangreville", "CAPPELLOTTO", "Profbreaker", "Sauerburger", "Brockmüller", "FBC Maniago", "Rostselmash",
			"Hako Yanmar", "Breviglieri", "Dala-Gripen", "Wagen-meyer", "Willenbrock", "CVS Ferrari", "Lampferhoff",
			"Katmerciler", "Scharmüller", "Meyer-Lohne", "Losenhausen", "Fejemaskine", "Ernst Vogel", "Skanderborg",
			"Le Tourneau", "Tune-eureka", "Peddinghaus", "MAX Trailer", "Multiforest", "Interdiesel", "Fjeldhammer",
			"Promax-Star", "Silverstone", "Duevelsdorf", "Stemplinger", "Wallenstein", "Powerlifter", "UniCarriers",
			"TotalLifter", "Grasshopper", "Trailmobile", "Matt Nielen", "Taylor-Dunn", "Earthmaster", "Votex Bison",
			"Watermaster", "Bjerringbro", "Michieletto", "Verti-Drain", "Wheel Horse", "Grain Saver", "Hanselifter",
			"Max Holland", "Nuova Detas", "Cherrington", "Elia Peroni", "Van Nobelen", "Christiaens", "Van Rooijen",
			"Visser-Dewa", "Worthington", "Dustcontrol", "Antti-Sampo", "Factory Cat", "Crushmaster", "ZBR MAN TGA",
			"Scan-Loader", "Deutz Allis", "King Kutter", "CrustBuster", "Dunham Lehr", "Minneapolis", "Wells Cargo",
			"Trailmaster", "Gulf Stream", "Load Lifter", "Thunderbird", "Range Rover", "Great Lakes", "Kyllingstad",
			"Bergmeister", "Timbermatic", "Whisperwatt", "Progressive", "Soucy Track", "Streumaster", "Siloking 10",
			"Gaffeltruck", "Hydromatik", "Casagrande", "Fiat-Allis", "Broadcrown", "Stavostroj", "Sennebogen",
			"Langendorf", "Transmanut", "Mitsubishi", "Volkswagen", "Kässbohrer", "Geringhoff", "John Deere",
			"Challenger", "Strautmann", "Deutz-Fahr", "Trelleborg", "Kverneland", "TimberKing", "Timberjack",
			"Verachtert", "Agrimaster", "Kongskilde", "Mattermacc", "Teknamotor", "Delco Remy", "Lombardini",
			"Denka-Lift", "Irmer+Elze", "Sundstrand", "Montenegro", "Bobinindus", "Strassmayr", "Bredenoord",
			"Trevibenne", "Eurocomach", "Köckerling", "Flötzinger", "Cheng Gong", "Land Rover", "Trackstack",
			"Commercial", "Marsk Stig", "KarhuKoura", "Dowdeswell", "Techmaflex", "Strickland", "Schweriner",
			"Kivi-Pekka", "Arctic Cat", "Klapituiko", "Europelift", "Tellefsdal", "Dhollandia", "Universeel",
			"Bombardier", "Kronenburg", "Silofarmer", "Metal-Fach", "Weserhütte", "Brookville", "Irrifrance",
			"TerraGator", "Gallignani", "Transporta", "Blomenröhr", "Achleitner", "Fasterholt", "Pennacchio",
			"Idrofoglia", "Vredestein", "Spijkstaal", "Del Morino", "Turbo Hoet", "Split-Fire", "Schliesing",
			"Feldbinder", "Stensballe", "Hammelmann", "Tielbürger", "Simplicity", "Wiedenmann", "Westermann",
			"Menzi Muck", "Schwamborn", "Düvelsdorf", "Edilgrappa", "Bartoletti", "Kompenhans", "Babelsberg",
			"Die Gothas", "Hüffermann", "Meusburger", "Inter-Tech", "Componenta", "De Angelis", "Silo-wolff",
			"Bohnenkamp", "Annaburger", "Snorkelift", "Structural", "Blackstone", "Fleetguard", "Hesselberg",
			"Polygrabbe", "Towerlight", "Migatronic", "Alfa Laval", "Sgariboldi", "Timberwolf", "Kyndestoft",
			"Harrington", "Baltimatic", "Harvestore", "Farm Force", "Flishugger", "Knocerling", "Flexi-Coil",
			"V.Mariotti", "Pozycjoner", "Blachdeker", "Om-Pimespo", "Billy Goat", "Veldhuizen", "Agco Allis",
			"Jonckheere", "Land Pride", "Transcraft", "Konecranes", "Verachterd", "Hunklinger", "Speedypipe",
			"Super Skub", "Campagnola", "Multi-Trac", "Lawn Genie", "Kariotakis", "Thermobile", "Franz Kahl",
			"Kahlbacher", "Akzo Nobel", "Verstraete", "Vandenberg", "Van Rooyen", "Baekelandt", "Buitendijk",
			"Verbruggen", "Labtechnic", "Earthmover", "Rutherfurd", "SmartSweep", "AM General", "Varmo Lift",
			"Kontorsbod", "Multiskopa", "Elektronik", "F.c.walker", "Shelbourne", "Scot Track", "Cedarapids",
			"Rockhammer", "Trail King", "UNVERFERTH", "Wheatheart", "Hutchinson", "TAYLOR-WAY", "Kelly Ryan",
			"Roto Grind", "Auto Crane", "Kello-Bilt", "McLaughlin", "Curbmaster", "New Leader", "Great Dane",
			"Interstate", "Ellinghaus", "Ranch King", "Vacmasters", "Enduraplas", "Screen USA", "Prime Tech",
			"Goodfellow", "Baumalight", "Midwestern", "Balemaster", "Easy-Kleen", "W.S. Tyler", "WESTENDORF",
			"Pilke-patu", "Fehrenbach", "Terminator", "BALKASTARE", "Kwerneland", "Galichanin", "Ulyanovets",
			"Prissänkt!", "Galtrailer", "VOLVO L50C", "Farmi-Trac", "LeTourneau", "Agromaster", "TIM Neuson",
			"Kawakenki", "Doppstadt", "FG Wilson", "Montabert", "Palazzani", "Palfinger", "Pezzaioli", "Nooteboom",
			"Noblelift", "Väderstad", "Blanchard", "Universal", "Brenderup", "Niftylift", "ESCHLBÖCK", "MaterMacc",
			"McCormick", "Zwrotnice", "Spearhead", "Arrowhead", "Locatelli", "Benazzato", "Alligator", "Eurosteel",
			"Bizzocchi", "Fieldking", "MultiQuip", "Weidemann", "Riverland", "Kinshofer", "Beerepoot", "Link-Belt",
			"Keestrack", "Mistgabel", "Hitzinger", "Filippini", "Filipinni", "Vistarini", "Scheuerle", "Otto Boge",
			"WACKENHUT", "Obermaier", "Pöttinger", "Firestone", "Westfalia", "Uniforest", "Fransgård", "McCloskey",
			"Europower", "Mantsinen", "Europress", "Finnsuoja", "Dinapolis", "Marttiini", "VERSALIFT", "Zunhammer",
			"Nordmeyer", "Husqvarna", "Pezzolato", "Olofsfors", "Ackermann", "ASAHIIGEN", "Northwest", "Sanderson",
			"NIEWIADOW", "Foresteri", "Multilift", "Varmolift", "Underhaug", "Cub Cadet", "Agromatic", "Indexator",
			"Blaw-Knox", "Friedrich", "Chevrolet", "Westbjörn", "Priestman", "Trouillet", "Chieftain", "Barreiros",
			"Religieux", "Techmagri", "Willibald", "Hendricks", "Goldhofer", "Montracon", "Degenhart", "SlurryKat",
			"Vogelzang", "Peterbilt", "Greenland", "Meierling", "Morgnieux", "Böhringer", "Balkancar", "Slootsmid",
			"Terranova", "Schrijver", "Jongerius", "Rumptstad", "Metaltech", "Versatile", "Krukowiak", "Landsberg",
			"Hürlimann", "BL Pegson", "Heizohack", "Longmarch", "Dethleffs", "Weinsberg", "Marchetti", "Caravaggi",
			"Wiedemann", "Portafill", "Antonelli", "Bokimobil", "Schörling", "Yamaguchi", "Hydromann", "Schneider",
			"Amco Veba", "Sweepster", "MEKOSVETS", "Comacchio", "Bourgault", "Hydralift", "Litostroj", "Ostermann",
			"Blumhardt", "Red Rhino", "TIMMERKLO", "Eberhardt", "Mads Amby", "President", "Auto Wrap", "Schmotzer",
			"Michaelis", "Greenmech", "Hägglunds", "Renholmen", "Telestack", "Falköping", "Allweiler", "Ferrymann",
			"Eurovagon", "Powerpack", "Bornemann", "Källefall", "Donaldson", "Sunflower", "Agromasar", "Logitrans",
			"Combilift", "Spragelse", "Eurosilos", "Weremczuk", "San Marco", "Gustrower", "WoodChuck", "Groundhog",
			"Scan-Agro", "Cleveland", "E-Z Drill", "Arcotherm", "Muir-Hill", "Assentoft", "Agrometer", "Greencrop",
			"Agro-Wikt", "McCulloch", "Commander", "Containex", "Rincheval", "Uniter 45", "Brinkmann", "Rf-system",
			"Wellpoint", "Steinbock", "Red River", "SpraCoupe", "White Lai", "Disenwang", "Crossland", "Translift",
			"Shindaiwa", "Translyft", "Manitowoc", "La Bounty", "Blue Bird", "Pemberton", "Load King", "Haybuster",
			"Lengerich", "Eurowagon", "Danubiana", "Longreach", "Masterpac", "Uniloader", "Danstoker", "Vielhaben",
			"Hünnebeck", "CleanGirl", "Westfield", "Valpadana", "BS Vognen", "Honey Bee", "Biemmedue", "Spierings",
			"Stenderup", "Buschhoff", "Hylleberg", "Wellmeyer", "Farm King", "Jabelmann", "ScanStone", "Wernsmann",
			"Silowolff", "Craftsman", "Brodd-Son", "Mossrider", "Goodsense", "Streu-Mix", "Vogelsang", "Kugelmann",
			"Eck-sicma", "Rheinland", "Boisselet", "Genkinger", "Charlatte", "Koutsikos", "Obrotnica", "Stautmann",
			"Terex PPM", "Marcopolo", "Prim Ball", "Bertolini", "Deboffles", "Schrauwen", "Vanhoucke", "Payloader",
			"Cata-Dyne", "Schoemans", "Hugo Beck", "Transnorm", "De Bruyne", "Freshpark", "Delmorino", "Eichinger",
			"LandPride", "Fillworth", "Auspactor", "Brentwood", "Supertino", "Schanzlin", "Hauswirth", "Hydrovane",
			"Agro-Masz", "Lilleseth", "Ssangyong", "Elgo-Plus", "Tecnoagri", "Parmitter", "Lindström", "Nicholson",
			"TERRAMITE", "Katolight", "Grass-Rol", "Energreen", "CRC-Evans", "Werk-Brau", "Balderson", "Solesbees",
			"Pettibone", "Versatech", "Harvestec", "Stoltzfus", "Loegering", "Trackless", "Bale King", "Ag Attach",
			"Broderson", "Beuthling", "Seed Hawk", "Gyro-Trac", "Hoelscher", "Worksaver", "Remlinger", "Trail-Eze",
			"Mcfarlane", "E-Z Trail", "Lode King", "Camoplast", "Stoughton", "Trailstar", "TOWMASTER", "Trailboss",
			"Dong Feng", "Econoline", "Transcube", "Excadrill", "Troy-Bilt", "TimberPro", "Diamond Z", "Versatrac",
			"Supertrak", "Princeton", "Hydra-Mac", "Vibrotech", "Fleetwood", "Diamond T", "Redi Haul", "Spinnekop",
			"Grab John", "Liftmoore", "Cart-Away", "Bala Agri", "Constmach", "Piquersa", "Vibromax", "Volvo BM",
			"Olympian", "Liebherr", "Haulotte", "Takeuchi", "Multione", "Hidromek", "Furukawa", "Fukuwara", "Cattaneo",
			"Kawasaki", "DrilTech", "Fruehauf", "Hausherr", "Vibropac", "Nordberg", "Himoinsa", "Grassair", "Jonsered",
			"Schäffer", "Merceron", "Schröder", "Broshuis", "Hinomoto", "strimech", "Thwaites", "Gaspardo", "Alliance",
			"Zeppelin", "Quivogne", "Michelin", "Feraboli", "Zwolnice", "LeBoulch", "Angeloni", "New Idea", "Bourgoin",
			"Trioliet", "Berthoud", "Entracon", "Linnhoff", "Lehnhoff", "Top-Agro", "Solideal", "Fairport", "Sumitomo",
			"Creemers", "Johnston", "Gottwald", "Macmoter", "Charlynn", "Shibaura", "Dennison", "Carlisle", "Aerolift",
			"Kleemann", "Knikmops", "Bluelift", "Werklust", "Zoomlion", "Gjerstad", "WAUKESHA", "Job-Mann", "Champion",
			"Hydraram", "Bradshaw", "Eurotrac", "Zijtveld", "American", "Xcentric", "Reintjes", "Van Hool", "Herkules",
			"Striegel", "Ruthmann", "Multitel", "Ommelift", "Komptech", "Benzberg", "Euromach", "Frumecar", "Eisemann",
			"Goodyear", "Niemeyer", "Michigan", "Obermann", "Kirchner", "Lochmann", "Farmtech", "Pfanzelt", "Kooi-Aap",
			"Finnboom", "Wärtsilä", "Junkkari", "Kel-Berg", "MultaVEX", "Nordsten", "Bogballe", "Baastrup", "HidroGib",
			"Dongfeng", "Heatwork", "Westeria", "Dynapack", "Mailleux", "Lundberg", "Biardzki", "Bergmann", "Rockster",
			"J I Case", "TecnoGen", "Everdigm", "Multicar", "Focolift", "Piacenza", "Esa-Patu", "Blizzard", "Patruuna",
			"Ferguson", "Siloking", "Finncont", "Prosilva", "PINGUELY", "OilQuick", "Lamberet", "Heracles", "Rockwell",
			"Barneoud", "Scanlift", "Broddson", "Raimondi", "Derruppe", "Guidetti", "Frandent", "Gregoire", "Caruelle",
			"Imperial", "Franquet", "Kromhout", "Mirofret", "Lecinena", "Gilibert", "Audureau", "Baselier", "AsiaKing",
			"Ten Cate", "Duraplas", "Pasquali", "CRESSONI", "Faucheux", "Accormat", "Kenworth", "Cadillac", "McDonald",
			"Zuidberg", "CHRYSLER", "Labounty", "Silvatec", "Rousseau", "Bear Cat", "Tigercat", "Veenhuis", "Pz-Vicon",
			"Grundfos", "McConnel", "Agrifull", "Schouten", "Veewagen", "Eurolift", "Veneroni", "Tubeline", "Steketee",
			"Meprozet", "Allround", "Hermeler", "Anderson", "Bürstner", "Stegsted", "Greentec", "Gierkink", "Vandaele",
			"Robinson", "Neumeier", "Boramtec", "Hoffmann", "Promodis", "Grunwald", "Club Car", "Pegoraro", "Jacobsen",
			"Schwarze", "Boschung", "Ramsones", "Triangle", "Ransomes", "Vibraram", "Cadillon", "Turbosol", "CORNIVER",
			"Bernardi", "Euromecc", "Windhoff", "Muratori", "Metalika", "EasyLift", "DigiTrak", "ArmaTrac", "Verdelli",
			"Agrignia", "Pfreundt", "Westtech", "Jungojet", "Acometis", "Mogensen", "Charlier", "Broddway", "Ahlstrom",
			"Koehring", "Gassmann", "Take-Job", "Yokohama", "Kiesling", "Gradmeko", "Spermann", "Kriegler", "Rototilt",
			"Hercules", "Kraintek", "VDL Bova", "Daihatsu", "Progress", "Tulo Loh", "Böckmann", "Qvickman", "BØCKMANN",
			"Kilafors", "McCauley", "Enorossi", "Wedholms", "Williams", "Wuhlmaus", "Eurolohr", "CONTRADE", "Nuffield",
			"LS Mtron", "Hedemann", "Marchner", "Bramidan", "Bernards", "Thyregod", "Dancover", "Goodride", "Farmtrac",
			"Prentice", "Klöckner", "Ecomatic", "Tomahawk", "Hagedorn", "Tunetank", "Stamford", "Altairac", "Zabudowa",
			"Tranders", "Brattvag", "Callesen", "Kronhout", "Spinpack", "Jydeland", "Skogsjan", "Scanvogn", "Hultdins",
			"Reggiana", "Fiatagri", "Dominoni", "Farm Gem", "Beinlich", "Snowstar", "Dantruck", "Svetruck", "Innolift",
			"Liftstar", "Præstbro", "Rexworks", "Farmatic", "Hydramet", "Boughton", "Martofte", "Messersi", "Mullerup",
			"Mosegård", "Egebjerg", "Dan-Corn", "Donslund", "Dantherm", "Reynolds", "Bagramet", "Broughan", "Marshall",
			"Alstrong", "Brochard", "Chillton", "Red Rock", "Asa-lift", "Trak-Met", "Vecoplan", "Zbiornik", "Fantuzzi",
			"Towmotor", "Liftking", "Telsmith", "Bussbygg", "Hydromak", "Munsters", "Scantrax", "Kinglink", "Sinotruk",
			"Changlin", "Steelbro", "Bishamon", "Bulthuis", "Deestone", "Gföllner", "LiftMach", "Mistrall", "Kearneys",
			"Palsonic", "Hydrotec", "Intradin", "Robuschi", "Rockland", "Peerless", "Farmhand", "Degelman", "National",
			"Gramegna", "Stöcklin", "Fontaine", "Hasekamp", "Brouwers", "Steinweg", "Schlüter", "Massenza", "Belshina",
			"Lydersen", "Agerskov", "Brantner", "Snowline", "Byggelit", "Häcksler", "Badalini", "Ugerløse", "Rozmital",
			"Parkland", "Edenhall", "PERUGINI", "Multihog", "Beilhack", "Gambetti", "Goudland", "Woodking", "Bay Vogn",
			"Roberine", "Auwärter", "AgroLand", "Kirovets", "Vanguard", "As-Motor", "Eurocomp", "Agri-Fab", "Grasdorf",
			"Fristein", "Heitling", "Bertsche", "Leveques", "Griptech", "Mobilift", "Airpress", "Hidrokon", "Pauselli",
			"Benedini", "Warynski", "Whiteman", "Berzoini", "Metalmec", "Van Dijk", "Delignie", "Bercomex", "Grisnich",
			"Coenders", "Meeuwsen", "Intrasit", "Degramec", "Slootweg", "Verhoest", "Peterson", "Keystone", "PressTek",
			"Teletrac", "Pongratz", "THOMPSON", "Sullivan", "Heatweed", "Pehtoori", "Ekengård", "Mariotti", "Frontoni",
			"Columbia", "Foss-Eik", "Sinoboom", "Linderöd", "Swedmach", "Anaconda", "Landquip", "Agriweld", "EM Bemac",
			"SuperPac", "Marklift", "Breining", "Superior", "Teledyne", "Wain-Roy", "Frontier", "Westward", "Sterling",
			"Bush Hog", "McMillen", "Mahindra", "Loftness", "Highline", "Roto-Mix", "Farm Aid", "Killbros", "Winpower",
			"Bergkamp", "SnowWolf", "Vac-Tron", "Brillion", "Wil-Rich", "Big John", "Lift-All", "Capacity", "Magnolia",
			"Reedrill", "Jamesway", "Trac Vac", "Vauxhall", "Dynaweld", "Rite-Way", "East Mfg", "Meridian", "E-Z Tech",
			"Bradford", "Hydro-Ax", "Franklin", "Marathon", "DuraTech", "Hogzilla", "Crawford", "Powermax", "Lippmann",
			"Cardinal", "Landrich", "Genpower", "Carelift", "Cherokee", "Höglunds", "Tuikomat", "Crushtek", "Hamsteri",
			"Househam", "Kronwald", "Rumpstad", "GlasLift", "Chausson", "Magliner", "Sky High", "Metzeler", "Pagliero",
			"Asiawing", "Chandler", "Poseidon", "Gilcrest", "Langfeld", "BAKSKOPA", "Pedrotti", "mercedes", "Dethlefs",
			"HYVÄKONE", "Righetti", "Manitou", "Schaeff", "Komatsu", "Dynapac", "Vermeer", "Bitelli", "Sambron",
			"Hyundai", "Hanomag", "Wirtgen", "Rexroth", "Hitachi", "Lissmac", "Benford", "Faraone", "Sullair",
			"Kobelco", "Dynaset", "Perkins", "Åkerman", "Hydrema", "Masalta", "Shantui", "Faresin", "Casappa",
			"Renault", "Tamrock", "Danfoss", "Samsung", "Schmitz", "UpRight", "Magirus", "Meiller", "Ahlmann",
			"Sinoway", "Sandvik", "Oertzen", "Renders", "Detroit", "Mecalac", "Chereau", "Case IH", "Galucho",
			"SkyJack", "cummins", "Landini", "Amazone", "Capello", "Manotti", "Rolland", "Maschio", "Fantini",
			"Ziegler", "Bencini", "Tornado", "Steiger", "Orthman", "Caffini", "Schmidt", "Agrimat", "Hesston",
			"Bressel", "Monosem", "Brendon", "vickers", "Western", "Geismar", "Siemens", "Tri-Flo", "Marcher",
			"Compair", "Novotny", "Barford", "Wynajem", "Niagara", "Kreatec", "Barreto", "Gallmac", "Allison",
			"Leyland", "Denison", "Phoenix", "Holaras", "Ljungby", "Venieri", "Farwick", "Bonneux", "Pladdet",
			"Genesal", "Liugong", "Mustang", "Dresser", "Kue-Ken", "Svedala", "Hendrix", "Selwood", "Nijhuis",
			"Cascade", "Hy-Brid", "Varisco", "Brevini", "Hazemag", "Grouser", "Lebrero", "Peugeot", "Backers",
			"Partner", "Citroën", "Italmek", "Dromone", "Kaelble", "Allmand", "Güttler", "Laverda", "Gehlmax",
			"Reschke", "Prinoth", "Liftlux", "Morooka", "Lissmag", "Snorkel", "Ezystak", "Ansaldo", "Junttan",
			"Lincoln", "Clipper", "Demarec", "Cormidi", "Kessler", "Kärcher", "Schwarz", "Ferrari", "Riedler",
			"Wörmann", "Unilock", "Epsilon", "Optimas", "Lindner", "Waratah", "Log Max", "LAMETER", "Mantall",
			"Padagas", "Gradall", "Stanley", "Mp-lift", "Carnehl", "Soilmec", "Müthing", "Neoplan", "Agrozet",
			"Scandia", "Loglift", "SOCOMEC", "Suokone", "grijper", "Lebotec", "Roadway", "Comedil", "Dressta",
			"Poclain", "Danfoil", "MB Trac", "Günstig", "Trimble", "Sunward", "Promove", "Wolagri", "Canycom",
			"Branson", "Quappen", "Delaval", "Tecnoma", "Jar-Met", "Euroram", "Kockums", "Chauvin", "Limetec",
			"Möslein", "Weckman", "Bentley", "Parator", "Sukkela", "Lumikko", "Bedford", "Livakka", "Karitek",
			"Multiva", "A-Faber", "Clayson", "Silocut", "Trapper", "Fordson", "Agrolux", "Wibergs", "Vistech",
			"Agronic", "Simulta", "Belarus", "Moteska", "Hustler", "Optimal", "Soukkio", "Rivakka", "Polaris",
			"Eco Log", "Richier", "Comilev", "Irisbus", "MATHIEU", "Castera", "Trailor", "Moiroud", "Piaggio",
			"Hubiere", "Berliet", "Spitzer", "Courant", "Nicolas", "Pietsch", "Secmair", "Comansa", "Gourdon",
			"Instant", "Mz Imer", "SECATOL", "Möckeln", "Kaldnes", "Schwing", "Endress", "Leitner", "Genesis",
			"Stetter", "Haahjem", "Twaites", "Sfoggia", "Pel-job", "Delimbe", "Perlini", "Jeantil", "Sørling",
			"Istrail", "Porsche", "Carrier", "Corinsa", "Stevens", "Terberg", "Trabosa", "Agrisem", "Carraro",
			"Daimler", "Goldoni", "Legrand", "Oshkosh", "Becchio", "Sodimac", "Pellenc", "Bunning", "Desvoys",
			"Agromet", "Pirelli", "Brimont", "Impulse", "Beretta", "Guillen", "Van Eck", "Sogedep", "Breston",
			"Ploeger", "Ezendam", "Miedema", "Agrimax", "Redrock", "Italdem", "Bijlsma", "Basrijs", "Perfect",
			"Saxonia", "Delvano", "Irrimec", "Agrifac", "Fliegel", "Gemelli", "Fiorini", "Mengele", "Ski-doo",
			"Agrosat", "Dammann", "Bucyrus", "Caprari", "Eurodem", "Giletta", "Heywang", "Demoter", "Ortolan",
			"Biojack", "Morbark", "Amkodor", "Zemmler", "Weichai", "Eurogru", "Shacman", "Atcomex", "Gravely",
			"Tennant", "Nilfisk", "Collino", "Edilgru", "hofmans", "Snapper", "Gmeiner", "Semeato", "Dragone",
			"Hofmann", "Dynamic", "Rovatti", "Kersten", "Dambach", "Peruzzo", "SweepEx", "Guschel", "Zamboni",
			"Maletti", "Mählers", "Gutbrod", "Einböck", "Applied", "Bendini", "Pomarol", "Italmec", "Cushman",
			"Grammer", "Diebolt", "Essemko", "Tsurumi", "Glogger", "Uni-väg", "Rotzler", "Cometto", "Minimax",
			"Keppler", "Baryval", "Hilltip", "Voegler", "Gumijas", "Esterer", "LM Trac", "Novatec", "Meritor",
			"Gunnebo", "Berkhof", "Wielton", "Humbaur", "Norfrig", "Härryda", "Hogstad", "Autosan", "Fraugde",
			"FarmGEM", "Vestmek", "Agrodan", "Agromec", "Mancini", "Fortuna", "Kranman", "Repossi", "Fiskars",
			"Buwalda", "Novarex", "Moelven", "Kellfri", "Husmann", "Carlton", "Schulte", "Arbocut", "Toplift",
			"Stenhøj", "Triumph", "Soneson", "Hanibal", "Sundyne", "Schlüte", "Bollnäs", "Maximum", "Schorch",
			"Ghedini", "Variant", "Orthaus", "Norgear", "Kherson", "Scantec", "Moffett", "Barkley", "Namyslo",
			"Famarol", "Maximal", "Montini", "Siewnik", "Arcusin", "Italmix", "Bomford", "Kiverco", "Fleming",
			"Rolmako", "Hi-Spec", "Claydon", "Kimadan", "Bovlund", "Bording", "Cormall", "Danline", "Stanhay",
			"Danregn", "Herborg", "Cimbria", "Bulldog", "Lindana", "Marston", "Mus-Max", "Gandini", "Supreme",
			"Veekmas", "Standen", "Montull", "Konings", "Classen", "Strumyk", "Warrior", "Fenwick", "Lonking",
			"Nichiyu", "Baumann", "Hangcha", "Rotomec", "Noremat", "Christy", "Herbert", "Varsico", "Manitex",
			"Collard", "Backhus", "Mauguin", "Lansing", "Sahntui", "Boomton", "Sollroc", "JQ Lift", "Munters",
			"Viberti", "Midland", "Robuste", "Vs-mont", "Yamacha", "Wiggins", "Tailift", "Trencor", "Raymond",
			"Flender", "Big Joe", "Mobicon", "Lencrow", "Hipower", "SkyTrak", "KPI-JCI", "Ag-Chem", "Fischer",
			"Pimespo", "Advance", "Artison", "Bolzoni", "Landoll", "Demarko", "Casella", "Hankook", "Everest",
			"Matilsa", "Dankvic", "Rondini", "Garford", "Danvægt", "Boscaro", "Generac", "Tonutti", "Alitrak",
			"Fiedler", "Poluzzi", "Bavaria", "Unicorn", "Huttner", "Arcomet", "DB Tech", "Minerva", "Solaris",
			"Hangler", "Tabbert", "Rainbow", "Barthau", "Gilbers", "Gassner", "Clemens", "Unifarm", "Forrigo",
			"Glutton", "Agrocom", "Ravenna", "Yardman", "Wematik", "Buffalo", "Busatis", "Rudolph", "Heilers",
			"Netagco", "Lagarde", "Meclift", "Armanni", "CITYNET", "Eurotec", "Roncari", "Belotti", "SmithCo",
			"Maximus", "Shaanxi", "Cytecma", "Johnson", "Blucamp", "Willett", "Keulmac", "Devette", "Cybutec",
			"Lauwers", "Koppert", "Potveer", "Pewisys", "Porreau", "Cologic", "Miracon", "Kiremko", "Bernard",
			"Lischka", "Oelkers", "Kretzer", "Mercury", "Heinola", "Striker", "Autocon", "Radicon", "MinQuip",
			"Gensafe", "Kroeger", "Giegold", "Frisian", "Vervaet", "Roadhog", "Lay-Mor", "Bloksma", "Ferrand",
			"Kranzle", "Sperber", "Hu-Lift", "Chicago", "hultins", "Mecanil", "Norslep", "Nödinge", "Tydraul",
			"Hamatzu", "Ventrac", "Comarth", "Skyking", "BlueMac", "Bil-Jax", "Brenner", "Spokane", "Kershaw",
			"Roadtec", "Spartan", "Rockram", "Pioneer", "Tes Car", "Aeromeh", "Actisol", "Holares", "Gleaner",
			"Hensley", "Bateman", "Finning", "Premier", "Autocar", "Kolberg", "Blu-Jet", "Wildkat", "Bedrock",
			"Hiniker", "Alloway", "Jay Lor", "Artsway", "Lucknow", "NorStar", "Economy", "Elliott", "Kewanee",
			"Danuser", "Mainero", "Mauldin", "Carlson", "Erskine", "Redball", "Concord", "Top Air", "McElroy",
			"Summers", "Swenson", "Salford", "Ashland", "Troxell", "Farmall", "Clement", "Outback", "Ver-Mac",
			"Deloupe", "Utility", "Hackney", "Diamond", "EnerSys", "Ledwell", "Glencoe", "Talbert", "Kaufman",
			"Liddell", "Doepker", "Trinity", "Big Tex", "Felling", "Vac-Con", "Galyean", "Mi-Jack", "Hilbilt",
			"Unicell", "Ohnsorg", "Pacific", "Leffers", "Bad Boy", "Knowles", "MAD VAC", "Mayrath", "Ez-Flow",
			"Ficklin", "RoGator", "Schaben", "Bestway", "WILLMAR", "Tanguay", "Rotobec", "Lamtrac", "Sellick",
			"Deister", "Wildcat", "Cornell", "Zanetis", "Benassi", "Freeman", "Ventura", "Fermont", "Hancock",
			"Bazzoli", "Broaryd", "Cimline", "Warfama", "Logitec", "Bredahl", "Malleux", "Schafer", "Stronga",
			"Ortomec", "Red Roo", "Reading", "Andover", "Bestorp", "Ezee-on", "Bartell", "Polonez", "Norwood",
			"Sudenga", "Agronik", "Stedman", "Paragan", "Vantage", "Annaite", "Fabtech", "Mandako", "Siromer",
			"Combcut", "Demblon", "A-Trans", "Dromech", "Agrocat", "Country", "AL-LIFT", "Hanwoo", "Betico", "Wacker",
			"Ammann", "Doosan", "Daewoo", "Bobcat", "Yanmar", "Rabaud", "Lutong", "Rammax", "Korota", "Aquila",
			"Evotec", "Kubota", "Nissan", "Soosan", "Protec", "Vögele", "Kaeser", "Melroe", "Polkon", "Parker",
			"Kraker", "Probst", "Serrus", "Pronar", "Scania", "Kramer", "Spicer", "Toyota", "Thomas", "Pegson",
			"Swepac", "Hammer", "Mikasa", "Peecon", "Master", "Oliver", "Nokian", "Valmet", "Horsch", "Krings",
			"Regent", "Saphir", "Olimac", "Lemken", "Överum", "Ponsse", "Neuson", "Welger", "Rekord", "Benmac",
			"Valtra", "Grecav", "Joskin", "Climax", "Hydrac", "Forigo", "Mascar", "Primex", "Hydros", "McHale",
			"Hassia", "Taarup", "Miller", "Pramac", "ArcGen", "Sumner", "Koshin", "Falcon", "Pop Up", "Hinowa",
			"Weimar", "Solmec", "Airman", "Tracto", "Benfra", "Puller", "Aeolus", "Holset", "Frisch", "Pegaso",
			"Haldex", "Kayaba", "Marrel", "Fraste", "Conrad", "Everun", "Bonhof", "Genset", "Bantam", "Scheid",
			"Unimog", "Müller", "Marini", "Ruston", "Lister", "Junjin", "Petbow", "WICKER", "Caesar", "Layher",
			"Dorman", "Allied", "Euclid", "Fermec", "EcoAir", "Garbin", "Zinser", "Potain", "Delmag", "Dehaco",
			"Kemper", "TyreOn", "Engcon", "Rammer", "Baxter", "Huddig", "Reisch", "Hyster", "Benalu", "KAISER",
			"Hammel", "Invepe", "Howard", "Kennis", "Holmes", "Finlay", "Topcon", "Kiefer", "Vantec", "Polyma",
			"Bräuer", "Fliegl", "Teupen", "Godwin", "Tadano", "Kröger", "Stauss", "Condor", "Wimmer", "Cedima",
			"Knapen", "Benati", "Norton", "Böhler", "Indeco", "Gruber", "Pichon", "Unsinn", "Mammut", "Hapert",
			"Samasz", "Igland", "Albach", "Oregon", "Tajfun", "Ramfos", "Dunlop", "Locust", "Lännen", "Warzee",
			"Kovaco", "Bowman", "Lokomo", "Bronto", "Yuchai", "Fintec", "Uchida", "Enerco", "Genmac", "Jaguar",
			"Storti", "Thaler", "Sokoro", "Luclar", "Farmet", "Perard", "Bredal", "Dal-Bo", "Veriga", "Leguan",
			"Samson", "Roland", "Attack", "Rotair", "Socage", "Crafco", "Alimak", "Taurus", "Grimme", "Dexter",
			"Prelog", "Kerner", "TIANLI", "Jensen", "Cranab", "Logset", "Nisula", "Magyar", "Metaco", "Kockum",
			"Skaala", "Lintec", "Jimeca", "Majava", "Tyllis", "Agados", "Berger", "Potila", "Norcar", "Kronos",
			"TimTec", "Pellon", "Hi-tec", "Krampe", "Holder", "Murska", "Somero", "Starco", "Propax", "Farmex",
			"Keenan", "Meiren", "Jykevä", "Trejon", "Alstor", "Rottne", "Viking", "Mesera", "Bodard", "Bargam",
			"Dragon", "Bonnet", "Warman", "Ermont", "Vandel", "Tesmec", "Karosa", "Legras", "Vammas", "Lorain",
			"Panien", "Dulevo", "YUTONG", "VALMAN", "Dingli", "Sherpa", "Snowek", "Uromac", "Demico", "Torgar",
			"Kalmar", "Kodiak", "Sebhsa", "Carmix", "Pacton", "Dennis", "Wilson", "Wagner", "Gergen", "Caldal",
			"Seguip", "Cochet", "Jeulin", "Custer", "Calvet", "Lodico", "Accord", "Tracon", "Someca", "Evrard",
			"Matrot", "Robine", "Storth", "HAMMAR", "Robert", "Molcon", "Goizin", "Bugnot", "Bonnel", "Kleber",
			"Bandit", "Naarva", "Menart", "Quicke", "Suzuki", "Dinkel", "Paccar", "Bucher", "Kembro", "Hekamp",
			"Douven", "Yamaha", "Sieger", "Becker", "Kaweco", "E-Z-GO", "Takraf", "Herder", "Farmax", "Meijer",
			"Cebeco", "Eicher", "Cosmag", "Oehler", "Rovadi", "Sommer", "Heuser", "Egyedi", "Imants", "Sitrex",
			"Can-am", "MacDon", "Ostler", "Talson", "Mandam", "Tigges", "Tehnos", "Alpego", "Dexwal", "TEAGLE",
			"Göweil", "Eisele", "Rotina", "Dücker", "Record", "Leffer", "Matbro", "Visser", "Hankmo", "Damcon",
			"Moheda", "Saelen", "Hayter", "Target", "Tuchel", "Walker", "Scarab", "Frappa", "Reform", "Struik",
			"Peiner", "Egholm", "Deleks", "Mattei", "Lehner", "Cramer", "Fricke", "Quadix", "Eduard", "Dautel",
			"Goupil", "Drivex", "Harmak", "DEVAKO", "Kohler", "Eggers", "Barkas", "Carrus", "Götene", "Austin",
			"Fering", "Schier", "Welgro", "Pachel", "Fjärås", "Siljum", "Aurepa", "Zanner", "Gorica", "Lifton",
			"Bicchi", "Insley", "Goebel", "Rancke", "Mistra", "Colmar", "Vreten", "Böcker", "Meyers", "Herbst",
			"Knoche", "Svecia", "Packer", "Fimaks", "Awemak", "Alucar", "Kleine", "Marbus", "Lindus", "Holmer",
			"Logman", "Valník", "Hilken", "Jacoby", "Rahbek", "Palmse", "Fisker", "Tokvam", "Cyclon", "Agreba",
			"Aerzen", "Duomat", "Thrige", "Al-vac", "Komaki", "Sperre", "CFMoto", "InTrac", "Ritter", "Siimet",
			"Farmer", "Joutsa", "Reekie", "Twinca", "Dolmar", "Petter", "Paxman", "Metsjö", "SUNFAB", "Eckart",
			"Zaslaw", "Moreau", "Roberg", "Cactus", "Linden", "Hüller", "Nobili", "Marcon", "Gisebo", "Bracke",
			"Weimer", "Voight", "Newage", "Perkum", "Hyundi", "A-Ward", "SHEHWA", "Wilcox", "Galaxy", "Armour",
			"Cheval", "Vestas", "Arikon", "Winget", "Petkus", "Cyklop", "Hubtex", "Kymppi", "Seacom", "Klimza",
			"Dewulf", "Bomech", "Wright", "Duport", "Skiold", "Vikmet", "Cappon", "Digger", "Dk-Tec", "Woprol",
			"Vilske", "Sulzer", "Trimax", "Schaad", "Marwel", "Tierre", "Keeway", "Regero", "Grillo", "Mammen",
			"Buhler", "Skjold", "Domino", "Haukka", "Daltec", "Apache", "Ddieci", "Balfor", "Bailey", "Altura",
			"Benett", "Oxdale", "Knight", "Cherry", "Hudson", "Alitec", "Neuero", "Chafer", "Watson", "Allman",
			"Fraser", "Jacrac", "Logmer", "Janpol", "Valmar", "Auramo", "Utilev", "Datsun", "Ningbo", "Etesia",
			"Morris", "Makita", "Normet", "Barmac", "Access", "Ag-Bag", "Symons", "Xuetao", "Rasant", "Carter",
			"Abelco", "Forway", "Permco", "Liming", "Ensign", "Contar", "Zwalve", "Triton", "Tirsan", "Maersk",
			"Galion", "Mitsui", "Minami", "Fotron", "Feeler", "Shinko", "Yutani", "Brandt", "Zenith", "Henred",
			"Fenner", "Longji", "Jaylor", "Rogers", "Witzco", "Sauter", "Maxein", "Durwen", "Stabau", "Hamech",
			"Eylert", "Vetter", "Gutler", "Caroni", "Alasco", "Tremix", "Sepson", "Bremer", "Hobart", "Murray",
			"Delfin", "Enteco", "Turner", "Subaru", "Danson", "Inveco", "Kemppi", "Braden", "Stomil", "Bednar",
			"Schopf", "Projet", "Skyman", "Prakla", "Handte", "Nissen", "Saurer", "Tandem", "Kumlin", "Bobman",
			"Wiberg", "Binger", "T-AX-O", "Hattat", "Navtek", "Sparco", "Dalian", "Stocka", "Baarck", "FERRIS",
			"Doubex", "Klippo", "Linhai", "Pietro", "Krause", "Magnum", "Garant", "Isaria", "Wanner", "Gigant",
			"Saviem", "Bobard", "Eureka", "Clivio", "Gomaco", "Drexel", "Birdie", "Gepter", "Hawker", "Tescar",
			"Stelco", "Timbco", "Toimil", "Laurak", "Abarth", "Burtec", "Tumoba", "Olimex", "Compas", "Greefa",
			"Da Ros", "Jamafa", "Demtec", "Manter", "Halmec", "Nobels", "Phiber", "Nagano", "Ideaal", "Krakei",
			"Langco", "Thilot", "Viscon", "Trakat", "Lisman", "Stolze", "Capway", "Hanmey", "Botman", "Kronen",
			"Mantis", "Ariens", "Seiler", "Alpina", "Jaques", "Marmix", "Cymasa", "Conver", "Al-jon", "Gansow",
			"Varsta", "Irmair", "RADWAG", "Preuss", "Bagela", "Bizien", "Hunter", "Sovema", "Connor", "Rotage",
			"Vezeko", "Camion", "Taylor", "Giraff", "Sävsjö", "Åkland", "Kazuma", "Sonnys", "Corghi", "Galfre",
			"Turfco", "Etnyre", "Börger", "Hüning", "Nikken", "Saturn", "Globus", "Hummer", "Fridge", "Gillig",
			"LeeBoy", "Bradco", "Tramac", "Huskie", "Ikarus", "Fukawa", "Gannon", "Empire", "Badger", "Garier",
			"Virnig", "Maurer", "Masaba", "Balzer", "Feterl", "Wishek", "Fuller", "Gentec", "Toucan", "United",
			"Weiler", "VACTOR", "Cotech", "Ingram", "Amadas", "Dorsey", "Cormac", "Quincy", "YETTER", "Fabrex",
			"Alkota", "Morgan", "Curtis", "Condux", "Ottawa", "Berlon", "Winkle", "Butler", "Benlee", "Dakota",
			"Fabtek", "Allvan", "Athens", "Tridem", "Benson", "Kidron", "Wabash", "Bigham", "Timpte", "Belshe",
			"Ravens", "Brazos", "Palmer", "Travis", "Ederer", "Barbco", "Exmark", "Wilmar", "WALDON", "Exodus",
			"Madill", "Blount", "Trelan", "Baldor", "Nissha", "Dayton", "Trojan", "Fabtec", "Essick", "Xtreme",
			"Elmers", "Olathe", "Koller", "Howden", "Forano", "Jaeger", "Browns", "Hopper", "Strick", "Allatt",
			"Pequea", "Kolman", "DeWalt", "Riecam", "Laweta", "EcoHog", "Nugent", "Diedam", "Fushun", "Ashita",
			"Orteco", "Bergen", "Sankoo", "Clarke", "Allett", "Acerbi", "Ardens", "Tarsus", "Spread", "Gilles",
			"Jammet", "Janmil", "Exmash", "Roagna", "Bredöl", "Re-Met", "Clemro", "finley", "MOFFET", "E-Ject", "Other",
			"Grove", "Terex", "Okada", "Volvo", "Krupp", "Dieci", "Fuchs", "Bomag", "Atmos", "Kamaz", "Genie", "Linde",
			"Mecbo", "Atlas", "Bravi", "Bauer", "Belle", "Deutz", "Demag", "Astra", "Bumar", "Tesab", "Still", "Lemac",
			"Iveco", "Stone", "Gesan", "Claas", "Suihe", "Heiwo", "Effer", "Extec", "Silla", "Setra", "Bosch", "Es-ge",
			"Delta", "Hardi", "Ursus", "Fendt", "Hymac", "Sulky", "Simba", "Egyéb", "Stoll", "Agram", "Stark", "Zanon",
			"Nobas", "Stehr", "Krone", "Nodet", "Foton", "Steyr", "Viper", "Sykes", "Halla", "Antti", "Allen", "Vicon",
			"Lucas", "Ginaf", "Clark", "Hurth", "Mosty", "Gremo", "Valla", "Astec", "Remko", "Rozzi", "Metso", "Hartl",
			"Magni", "Amida", "Merlo", "Digga", "Weiro", "Pilot", "Comet", "Iteco", "Isuzu", "Bowex", "Wabco", "Eaton",
			"Zetor", "Sachs", "Power", "Rotar", "Geith", "Klepp", "Aichi", "Vietz", "Meyer", "Vitra", "Kipor", "Kasto",
			"Sakai", "Klein", "Skako", "DAVID", "Rossi", "Raygo", "Stahl", "Hanix", "Antec", "Klemm", "Korte", "Nachi",
			"Henke", "Fasti", "Isoli", "GiANT", "Simex", "Flygt", "Simma", "Avant", "Coder", "Hytec", "Fassi", "Arjes",
			"Samro", "Wolff", "Wumag", "Mazda", "Posch", "Hütte", "Wirth", "Maeda", "Denyo", "Pewag", "Hanta", "Bison",
			"Tecna", "Komac", "Würth", "Erkat", "Kögel", "Tirre", "Skoda", "Adler", "Weber", "Fella", "Lomma", "Hauer",
			"Kesla", "Farmi", "Tapio", "Nokka", "Stepa", "Moipu", "Tiger", "ARDEN", "Coles", "Tatra", "Laten", "Rinne",
			"Brokk", "PRAGA", "Acmar", "Brown", "Kemco", "Abbey", "Vahva", "He-Va", "Rauch", "Kinze", "Strom", "Ozgul",
			"Zorzi", "Kroll", "Epoke", "Akpil", "Chief", "Nesbo", "Witte", "Arrow", "Hilti", "Conow", "Berti", "Profi",
			"Kenco", "Tanco", "Agrex", "Mitas", "Daemo", "Berco", "Farma", "Alcoa", "Logic", "Ecomp", "Närko", "Lexus",
			"AR-PE", "Ekeri", "Riiko", "Rolfo", "Matec", "Kaupe", "Respo", "Norba", "Gehab", "Saris", "Briab", "BIGAB",
			"Ilsbo", "HA-ME", "Jorpe", "Wiler", "Muuli", "Karhu", "Varmo", "Leppä", "Velsa", "Tuhti", "Karko", "Hakki",
			"Sampo", "Ferri", "Tempo", "Rasco", "E-ton", "Jussi", "Wulff", "Palax", "Mörtl", "Honda", "Matti", "Vesme",
			"Tarmo", "Holms", "Funki", "Rysky", "Vredo", "Kotte", "Palmu", "Faber", "Stiga", "Bruks", "Estre", "Magsi",
			"Seppi", "Verem", "Bunge", "Temsa", "Dalbe", "Cobra", "Camac", "DUMEC", "Movex", "Nemek", "Menci", "Razol",
			"Emily", "Forus", "Suire", "Klaus", "Huard", "Braud", "Boxer", "Fiori", "Morra", "Idass", "Foden", "Pesci",
			"Orkel", "Kempf", "Floor", "Altec", "Titan", "Draco", "Dodge", "Fenet", "Roger", "Jumbo", "Smart", "Hypro",
			"Камаз", "Jekko", "Derot", "Eimco", "Iseki", "Evers", "Comac", "Tulip", "Celli", "Dubex", "Cesab", "Desta",
			"Gruse", "Jinma", "Prima", "Ceres", "meyco", "Zocon", "Simon", "Georg", "Smitz", "Mayer", "Dondi", "Tidue",
			"Nardi", "Sicma", "Suzue", "Tuber", "Excel", "Stihl", "Remac", "Hagie", "Omarv", "Dacia", "Lasco", "Greco",
			"Wecon", "Frick", "Indox", "Desot", "Elkon", "Eliet", "Fecon", "Growi", "Voith", "Gross", "Neman", "Agria",
			"Taski", "Heylo", "Kioti", "Nimos", "Libra", "Rolba", "Green", "Votex", "Agric", "Leica", "Fiona", "Copma",
			"Sicom", "Ladog", "Hansa", "Briri", "Huber", "Mulag", "Rofan", "Norje", "Köppl", "Terra", "Detas", "Negri",
			"Chemo", "Al-Ko", "Ormig", "HEATH", "Gomar", "Bomet", "Berko", "Gamon", "Axeco", "Talex", "Serra", "Sisis",
			"Bemab", "Palms", "Hymas", "Spier", "Wille", "Exero", "Hilse", "KA-BA", "Bless", "Karfa", "Hüdig", "Megas",
			"Wiese", "Barum", "Resta", "Ebert", "Thule", "Menke", "Orten", "Panav", "NOKAB", "Raven", "Trima", "Frost",
			"Hiabo", "Kumho", "Nymek", "Mekos", "Mauer", "Jacto", "Solis", "Drago", "Dingo", "Hecht", "Tebbe", "Belos",
			"Lagab", "Bodex", "Demco", "Samon", "Inuma", "Dalum", "Humus", "Rimas", "Limas", "Davis", "Jones", "Brøyt",
			"Solus", "Welte", "König", "Nyvab", "Clena", "Pefra", "Bejco", "Desmi", "Trump", "Smith", "Hymax", "Zremb",
			"Guhur", "Thumm", "Eldan", "Blyss", "Hadef", "Intho", "Aixam", "Graco", "Stama", "Moret", "Krøll", "Stena",
			"Faxes", "Tacke", "Brevo", "L & S", "Vimek", "Alkom", "Armor", "Butti", "Varig", "Agrip", "Antha", "Crown",
			"Sipma", "Kelly", "Ovlac", "Major", "Rocla", "Atlet", "Kunto", "Heden", "Abeko", "Holsø", "Tytan", "Expom",
			"Woods", "Runni", "Skals", "Altro", "Nolan", "Datho", "Meton", "Handy", "Ocmis", "Harsø", "Forst", "Tinaz",
			"Elkær", "Kuhmo", "Renix", "Wylie", "Braun", "Stema", "Twose", "Biber", "Rolki", "Krpan", "Maxim", "Malwa",
			"Varta", "Irion", "Flexi", "Brock", "Worms", "Brodd", "Atila", "Hisun", "Eriez", "Humer", "White", "Stork",
			"Botex", "Beyne", "Benne", "Eagle", "Minyu", "Xunte", "Goman", "Craig", "Carco", "Rhino", "Polar", "Bruns",
			"Socma", "Netam", "Izumi", "Euroe", "Samil", "Afrit", "Samag", "Busaf", "Hemos", "Lucar", "Omega", "Noram",
			"Gator", "Lafis", "Melex", "Hoist", "Lugli", "Samuk", "Carer", "Prins", "Magma", "Brevi", "Banut", "Cardi",
			"Allis", "Untha", "Bizon", "Nante", "Dinli", "Gerni", "Conor", "Trimo", "Befco", "Texas", "Kymco", "D-tec",
			"Sukup", "Evans", "Remet", "K-A-G", "Fobro", "Adige", "C-Dax", "Dexta", "Jelau", "Dutzi", "Timan", "Nibbi",
			"Kenda", "Houle", "Himel", "Fulda", "Hesse", "Miele", "Royer", "Gyrax", "Baoli", "Seith", "Semax", "Bendi",
			"Simai", "Royal", "Xilin", "Turbo", "Giove", "Valve", "Mayco", "Sovam", "Ferve", "Kobra", "Hypac", "INDOS",
			"Knaus", "Demac", "Perdu", "Aweta", "Empas", "Flier", "Wamel", "Beets", "Boels", "Manac", "Sitma", "Sosef",
			"Prior", "Alubo", "Wevab", "Lango", "Frato", "Dofra", "Ebara", "James", "Chery", "Oniar", "Blast", "Kason",
			"MCWEL", "Airco", "Tampo", "Hitec", "Horst", "Frema", "Satex", "Awrol", "Heide", "Engen", "Wilco", "Ferbo",
			"Bächt", "Wirax", "Franz", "Kress", "Huhki", "Barko", "Jutul", "Dalen", "Extex", "Hymer", "VOMER", "Huahe",
			"Rapid", "Gemsa", "Fritz", "Akron", "Johan", "Rosco", "Nexen", "Trilo", "LeRoi", "Elgin", "Broce", "Ateco",
			"Ampac", "Almac", "Rover", "Oelle", "Hobby", "Parma", "Fleco", "Dymax", "Husky", "Entek", "Felco", "Notch",
			"Cepco", "Batco", "Stout", "Walco", "Bowie", "Alamo", "Howse", "Rowse", "REMCO", "Penta", "Allie", "Jiffy",
			"Maxon", "Cubex", "Kalyn", "Hayes", "E-one", "Greer", "Rugby", "Addco", "Pitts", "Exide", "Aspen", "Arnes",
			"Noble", "Brent", "Kühne", "Falls", "Adams", "Du-Al", "Cozad", "Drott", "Alloy", "Hobbs", "Athey", "Davey",
			"Rayco", "Dixon", "McKee", "K-Tec", "Loral", "Tyler", "Tymco", "DEERE", "Hough", "Harlo", "Beyer", "Irock",
			"Eljay", "Rahco", "Valew", "Caron", "Latre", "Aztec", "Ranco", "Beall", "Baker", "Laron", "Irtec", "Aktiv",
			"Steck", "Teijo", "Jetco", "Idaas", "Brady", "Wagen", "Quest", "Henne", "Dumpy", "Robby", "Fimag", "Umega",
			"LINCK", "Movax", "CASE", "Hamm", "Geda", "Mosa", "Kato", "Bell", "GIPO", "Boge", "Alup", "Mase", "Ausa",
			"Sdmo", "Ford", "Cifa", "Hiab", "Same", "Doll", "Rohr", "Fiat", "Lely", "Gard", "Kuhn", "Ural", "Naud",
			"Rabe", "Faun", "Dino", "Ilgi", "Agro", "Fast", "Moxy", "Etec", "Eder", "Long", "Omme", "Hatz", "Hawe",
			"Unex", "Bean", "Beco", "XCMG", "Lubo", "Toyo", "Eran", "Revo", "Bema", "Meba", "Icem", "Sany", "Hilo",
			"Tata", "Elme", "Gehl", "Luna", "Hino", "Esco", "Paus", "Wifo", "Cela", "Mait", "Mack", "Aska", "Esab",
			"HDPE", "Abus", "Lohr", "Unic", "Sket", "Onan", "Tabe", "Seat", "Allu", "Orsi", "Patu", "Geel", "Keto",
			"Lako", "Avia", "Pome", "Toro", "Reko", "Opel", "Elin", "Tana", "Agma", "Renk", "Genk", "Tume", "Vama",
			"Riko", "Imer", "Unia", "Mepu", "Kraz", "Star", "Fort", "Saez", "York", "Rote", "Burg", "Salo", "Deso",
			"Vila", "Edge", "Beha", "Esve", "Sisu", "Fuso", "Nopa", "Lipe", "Jyki", "Boro", "Leci", "Kome", "Hyva",
			"Hese", "Sami", "Esko", "Seko", "Kire", "Kova", "King", "Raju", "Juko", "Kasi", "Japa", "Elho", "Finn",
			"Pomo", "Oxsa", "Lame", "Wolf", "Vamo", "Ysta", "Jake", "Kipa", "Sine", "Teko", "Vepi", "Hapa", "Jenz",
			"PIKO", "Kopo", "Airo", "Midi", "Asca", "Tico", "ACTM", "Trax", "Mega", "Stas", "Cams", "Hako", "Fogo",
			"Duun", "Alba", "Ulma", "Vaia", "Jaso", "Euro", "Sale", "Caes", "Biso", "Falc", "Maur", "Damm", "Cobo",
			"Ravo", "Karl", "Doda", "Hill", "Jost", "Ebro", "Gofa", "Saab", "Mowi", "Audi", "XGMA", "ZZBO", "Jeep",
			"Riwo", "Nido", "Famo", "Ktwo", "Yale", "Zibo", "Tawi", "Jako", "Zago", "Berg", "Penz", "Isal", "Adam",
			"Fahr", "Alto", "Remu", "INOX", "Loma", "Amac", "Echo", "SDLG", "Zürn", "Kent", "Peri", "Sabo", "Jema",
			"Boki", "Efco", "Cemo", "Pfau", "Seka", "Toku", "Emat", "Kock", "Acco", "Econ", "Visa", "Boss", "Baer",
			"Mabo", "Asea", "Wefa", "Liaz", "Chtz", "Ricö", "KAMA", "Hoes", "Alfa", "Zako", "Bova", "Jung", "Svan",
			"Hall", "Joab", "BEFA", "Hafo", "TIVE", "Skab", "Olby", "Baas", "SENA", "Meko", "Dafe", "Brix", "Bala",
			"Viby", "Kone", "Pram", "Ipro", "Möre", "Goes", "Reck", "Erjo", "Ahwi", "Aebi", "Duks", "Edur", "Omas",
			"Sima", "Dapa", "Geho", "Lewa", "Ceha", "Gras", "Fuji", "Häny", "Veto", "Lima", "Niab", "Dasa", "Axel",
			"Funk", "Mafi", "Nico", "Kaup", "Atom", "Hema", "Camc", "Tong", "Koja", "Ares", "Rako", "Pomi", "Jyfa",
			"Ekko", "Erin", "AKSA", "Gejs", "Blec", "Lynx", "Reco", "Agco", "Kart", "Sala", "Renn", "Heli", "TREK",
			"Mora", "Ione", "Beta", "CNSE", "Faxe", "Miro", "Kovo", "Drum", "Heil", "Zvvz", "Tric", "Trio", "B-xl",
			"Pope", "Sumi", "Nash", "EZGO", "Emaq", "Yang", "Xava", "Nado", "Bukh", "Lang", "Rome", "Gram", "Reno",
			"Link", "Koch", "Kane", "Solo", "Ropa", "Gøma", "Paul", "Baos", "Böse", "Volk", "Rába", "Baka", "Scag",
			"Irus", "Becx", "Vogt", "Seco", "MIAG", "Atib", "VIZA", "Taks", "Javo", "Vito", "Hoaf", "Heto", "Haas",
			"Gege", "Elco", "Koat", "Mafo", "Pump", "Fini", "Oros", "Samo", "Salf", "Mafa", "Sizu", "Agbo", "Sumo",
			"Vabo", "Fram", "Fabo", "Hale", "Agri", "Suma", "Cabe", "ADCO", "Dion", "IMAC", "Leon", "Patz", "DARF",
			"Bros", "Bron", "CIMC", "Atco", "Amco", "Icon", "Raco", "Kory", "Argo", "Asco", "Tusk", "Lull", "Tota",
			"Dewa", "Howo", "Vang", "Sino", "Nopu", "Mele", "Vohl", "Epok", "kara", "Lada", "Semi", "Ritz", "Bray",
			"Oxbo", "Pace", "Lanz", "Joka", "Vält", "Isme", "ELBA", "O&K", "JCB", "JLG", "Mad", "ABL", "PPM", "SEC",
			"YTO", "VTN", "DAF", "NPK", "ABG", "MAN", "MEC", "UTB", "SIP", "RMH", "BvL", "REV", "Rau", "MTZ", "Rud",
			"NHS", "HBM", "SCS", "KSW", "KSB", "MBU", "Pom", "MAC", "DHB", "JMG", "MWM", "ZTS", "MTE", "IFA", "ATN",
			"CTE", "HTC", "MTU", "ATS", "GMC", "Fai", "ORU", "GTS", "AMA", "Joy", "IMI", "JRB", "NCK", "HGT", "AGT",
			"P&H", "HAB", "BKT", "SMC", "EGT", "ABS", "BBA", "VTM", "IHI", "SBM", "HSW", "AVK", "ABI", "HMF", "ARB",
			"MTS", "MGF", "BMT", "MFL", "PTH", "SMP", "BMW", "FRD", "Ofa", "SBL", "KMB", "GSR", "LST", "OCM", "TCM",
			"OKB", "TKD", "ZDT", "CTC", "BSS", "MSB", "SMS", "MAZ", "C&F", "Tim", "CNH", "FMG", "NTC", "M&J", "IMT",
			"ABB", "HSM", "HSP", "MOL", "PMC", "NTM", "ATC", "SDC", "VAK", "EHM", "RKP", "AJK", "BPW", "HFR", "LAG",
			"VDL", "CMT", "HLA", "EOS", "TGB", "Eho", "MTD", "YLÖ", "ROC", "Evi", "Ålö", "CLC", "DJB", "OVA", "ETA",
			"YSM", "MKG", "AMV", "KCP", "MBI", "EKW", "CMN", "AHP", "BSL", "AVR", "SAF", "Sor", "JPM", "ATM", "ERF",
			"SMB", "BCS", "SEM", "BIM", "CLM", "BPI", "NDH", "AGM", "BCW", "HEK", "RTS", "FAE", "APV", "CHD", "Gea",
			"VGM", "SAC", "Eke", "PVE", "Geo", "INO", "WKM", "SMZ", "CBI", "МАЗ", "OSA", "NLB", "RCM", "газ", "Gil",
			"BOS", "WLP", "MTB", "UFO", "RAM", "XYZ", "SBS", "BMC", "FGM", "FAP", "TAM", "DTM", "Mfh", "AWA", "BMS",
			"SMA", "IHC", "PLS", "HRD", "LMR", "SLP", "Uni", "MGM", "NHK", "RCD", "VKA", "ASG", "VEB", "LKT", "KGK",
			"BGU", "NFB", "MST", "UTC", "KMA", "REM", "GMR", "VEM", "AEG", "Elm", "TOS", "BSV", "MAS", "H&W", "FLS",
			"SKL", "Gaz", "GHH", "IPS", "IPC", "AMT", "SEE", "BMF", "GMF", "PRM", "FAW", "BBG", "ESA", "TYM", "JOS",
			"BJD", "SAM", "SAK", "MBW", "Ljh", "ACJ", "BTM", "VMS", "Krm", "FTG", "Log", "DBF", "J&M", "REX", "AFM",
			"Cam", "ABT", "FAC", "ACM", "TKS", "LMV", "FMV", "YPV", "JBS", "PMI", "LPG", "HTF", "CMI", "WOT", "M&G",
			"Kia", "PSP", "Jmc", "BSA", "SMV", "FMC", "CWS", "LMC", "JKB", "OMG", "HFX", "TST", "MIC", "LOC", "NDI",
			"ACS", "HAP", "DMS", "HWH", "OMD", "Aza", "Ace", "HCW", "BMD", "VPM", "RKM", "MBB", "BAR", "HBG", "HFT",
			"M&W", "STP", "MBK", "KTM", "RMF", "DAN", "LTB", "CAP", "JAC", "ASP", "HDK", "DNB", "XCG", "DWO", "SBO",
			"TTA", "MFG", "GCS", "BMV", "JAK", "Moi", "BFT", "BSM", "ORY", "BTI", "FFC", "WBM", "C&P", "TAG", "H&H",
			"AMI", "BBI", "DMI", "H&S", "NDE", "QMC", "Box", "CCC", "WRT", "BMY", "KBH", "KMC", "TYE", "PSM", "M&M",
			"ATI", "SMI", "CPS", "Jet", "Sym", "A&L", "CSI", "MMD", "JBX", "ASV", "CEC", "TCI", "AFA", "STI", "IWT",
			"DMC", "GVM", "DON", "Arm", "Ztr", "MDW", "Mcm", "HME", "AKU", "TBM", "MZ", "PC", "ZF", "OM", "SM", "LS",
			"MS", "KL", "GF", "HP", "ES", "MB", "AP", "VM", "GM", "KS", "TL", "SP", "GB", "PM", "AT", "JF", "HS", "GS",
			"NC", "GT", "MG", "BM", "PZ", "MX", "TP", "M3", "FM", "ST", "SB", "MT", "TR", "BT", "AB", "PS", "SV", "JM",
			"MO", "AM", "BV", "WM", "IH", "RC", "ER", "EP", "SH", "TT", "Mi", "LM", "UD", "PB", "TH", "HT", "KT", "NH",
			"TB", "JH", "PJ", "GP", "LT", "VW", "HAMM", "JCB", "SAMI", "CASE", "JCB", "LELY", "SCAG", "TORO", "DAF",
			"SDC", "JPM", "KRM", "AHP", "ERF", "MAN", "CX" };

	public static WebElement getWebElement(WebDriver driver, By by) {
		WebElement ele = driver.findElement(by);
		return ele;
	}

	public static void MouseOver(WebElement ele) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()", ele);
	}

	public static int getRandomIntwithinRange(int low, int high) {
		Random random = new Random();
		int randomNum = random.nextInt((high - low) + 1) + low;
		return randomNum;
	}

	public static int getDigitCountInPhone(String phone) {
		int count = 0;
		for (int i = 0; i < phone.length(); i++) {
			if (Character.isDigit(phone.charAt(i))) {
				count++;
			}
		}
		return count;
	}

	public static WebDriver loadUrl(WebDriver driver, String url) {
		boolean loaded = false;
		int count = 0;
		while (!loaded) {
			try {
				if (count >= 15) {
					return driver;
				}
				if (count >= 2) {
					driver.quit();
					waitTill();
					driver = getDriver();
				}
				checkAndWaitForInternetConnection(driver);
				driver.get(url);
				loaded = true;
			} catch (TimeoutException e) {
				count++;
				System.out.println("Problem while loading url " + e.getMessage());
				System.out.println(url);
				waitTill();
				loaded = false;
			} catch (Exception e) {
				count++;
				System.out.println("Problem while loading url " + e.getMessage());
				System.out.println(url);
				waitTill();
				loaded = false;
			}
		}
		return driver;
	}

	public static void checkAndWaitForInternetConnection(WebDriver driver) {
		boolean internetConnected = false;
		while (!internetConnected) {
			String site = "google.com";
			Socket sock = new Socket();
			InetSocketAddress addr = new InetSocketAddress(site, 80);
			try {
				sock.connect(addr, 3000);
				internetConnected = true;
			} catch (IOException e) {
				internetConnected = false;
				System.out.println("There Is No Internet Connection Waiting For Internet....!");
				try {
					Thread.sleep(2000);
					driver.navigate().refresh();
				} catch (InterruptedException e1) {
				}
			} finally {
				try {
					sock.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void checkAndWaitForInternetConnection() {
		boolean internetConnected = false;
		while (!internetConnected) {
			String site = "google.com";
			Socket sock = new Socket();
			InetSocketAddress addr = new InetSocketAddress(site, 80);
			try {
				sock.connect(addr, 3000);
				internetConnected = true;
			} catch (IOException e) {
				internetConnected = false;
				System.out.println("There Is No Internet Connection Waiting For Internet....!");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
				}
			} finally {
				try {
					sock.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String setAdditionalInfo(String beforeText, String key, String val) {
		String finalTExt = "";
		if (!beforeText.isEmpty()) {
			finalTExt = beforeText + "<next>";
		}
		finalTExt += "\"" + key + "\":\"" + val + "\"";
		return finalTExt;
	}

	public static void refreshBrowser(WebDriver driver) {
		boolean loaded = false;
		while (!loaded) {
			try {
				driver.navigate().refresh();
				loaded = true;
			} catch (Exception e) {
				loaded = false;
			}
		}
	}

	public static List<WebElement> getWebElements(WebDriver driver, By by) {
		List<WebElement> lis = driver.findElements(by);
		return lis;
	}

	public static boolean verifyElementPresent(WebDriver driver, By by) {
		try {
			if (getWebElement(driver, by).isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getStringWithCamelCase(String string) {
		String finalString = "";
		try {
			String[] splt = string.split(" ");
			for (String s : splt) {
				String firstChar = s.substring(0, 1).toUpperCase();
				String restOfTheChar = s.substring(1).toLowerCase();
				finalString += firstChar + restOfTheChar + " ";
			}
		} catch (Exception e) {

		}
		return finalString.trim();
	}

	public static void click(WebDriver driver, By by) throws Exception {
		int count = 1;
		for (int i = 1; i <= 1; i++) {
			try {
				if (count > 2)
					throw new Exception();
				getWebElement(driver, by).click();
			} catch (StaleElementReferenceException e) {
				--i;
				count++;
			} catch (ElementNotVisibleException e) {
				--i;
				count++;
			} catch (WebDriverException e) {
				--i;
				count++;
			}
		}

	}

	public static String getText(WebDriver driver, By by) {
		try {
			return getWebElement(driver, by).getText().trim();
		} catch (Exception e) {
			return "";
		}
	}

	public static String getText(WebElement ele) {
		try {
			return ele.getText().trim();
		} catch (Exception e) {
			return "";
		}
	}

	public static String getAttribute(WebDriver driver, By by, String attribute) {
		try {
			return getWebElement(driver, by).getAttribute(attribute).trim();
		} catch (Exception e) {
			return "";
		}
	}

	public static boolean isElementFound(final WebDriver driver, final By by) {
		boolean status = false;
		for (int i = 1; i <= 2; i++) {
			if (verifyElementPresent(driver, by)) {
				status = true;
				break;
			} else {
				waitTill(1000);
				if (i == 1)
					System.out.println("Looking for element with id" + by.toString());
			}
		}
		return status;
	}

	public static boolean waitForElementToVisible(final WebDriver driver, final By by) {
		boolean status = false;
		for (int i = 1; i < 60; i++) {
			if (verifyElementPresent(driver, by)) {
				status = true;
				break;
			} else {
				waitTill(1000);
			}
		}
		return status;
	}

	public static boolean sendKeys(WebDriver driver, By by, String data) {
		try {
			getWebElement(driver, by).clear();
			getWebElement(driver, by).sendKeys(data);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void waitForElement(WebDriver driver, By by) {
		for (int i = 0; i <= 5; i++) {
			if (!verifyElementPresent(driver, by)) {
				waitTill(1000);
			} else {
				break;
			}

		}
	}

	public static void waitForTwoElements(WebDriver driver, By by) {
		for (int i = 0; i <= 40; i++) {
			if (!verifyElementPresent(driver, by)) {
				waitTill(1000);
			} else {
				break;
			}

		}
	}
	public static WebDriver getDriver_new() {
		File addonpath = new File(System.getProperty("user.dir") + "\\Adblock_CRX\\Adblock-Plus_v3.1.crx");
		String browser = System.getProperty("browser");
		if (browser == null)
			browser = "chrome";
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("profile.default_content_settings.popups", 0);
			ChromeOptions options = new ChromeOptions();
			if (System.getProperty("proxy") != null) {
				// options.addArguments("--proxy-server=85.172.60.112:80");
				options.addArguments("--proxy-server=194.226.88.58:3128");
			}
			if (siteName == null) {
				siteName = "www.maskinbladet.dk";
			}
			if (siteName.equalsIgnoreCase("tuttocarrellielevatori.it")) {
				options.addArguments("--proxy-server=182.73.168.68:32088");
			}
			if (siteName != null) {
				if (siteName.equalsIgnoreCase("www.maskinbladet.dk"))
					options.addArguments("--disable-extensions");
				else {
					options.addExtensions(addonpath);
				}
				if (siteName.equalsIgnoreCase("agronetsl") || siteName.equalsIgnoreCase("mobile.de")
						|| siteName.equalsIgnoreCase("tractorpool")) {
					options.addExtensions(addonpath);
					addonpath = null;
					addonpath = new File(System.getProperty("user.dir") + "\\Adblock_CRX\\UltraSurf.crx");
					options.addExtensions(addonpath);
				}
			} else
				options.addExtensions(addonpath);
			options.addArguments("--test-type");
			options.addArguments("start-maximized");
			options.addArguments("disable-popup-blocking");
			options.setExperimentalOption("prefs", prefs);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			WebDriver driver = new ChromeDriver(capabilities);
			waitTill(5000);
			switchTab(driver);
			return driver;
		} else {
			return null;
		}
	}
	public static WebDriver getDriverProxy() {
		File exepath = new File(
				System.getProperty("user.dir")
						+ "\\UltrSurf\\UltraSurf-Security,-Privacy-&-Unblock-VPN_v1.5.4.crx");
		String browser = System.getProperty("browser");
		if (browser == null)
			browser = "chrome";
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\chromedriver.exe");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("profile.default_content_settings.popups", 0);
			ChromeOptions options = new ChromeOptions();
			if (System.getProperty("proxy") != null) {
				options.addArguments("--proxy-server=188.133.136.10:47113");
			}
			options.addExtensions(exepath);
			options.addArguments("--test-type");
			options.addArguments("start-maximized");
			options.addArguments("disable-popup-blocking");
			options.setExperimentalOption("prefs", prefs);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(
					CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
					true);
			WebDriver driver = new ChromeDriver(capabilities);
			switchTab(driver);
			return driver;
		} else if (browser.equals("htmlunit")) {
			WebDriver driver = new HtmlUnitDriver();
			return driver;
		} else {
			return null;
		}
	}


	public static void switchTab(WebDriver driver) {            
	    try {
	        Set<String> windows = driver.getWindowHandles();
	        Iterator<String> iter = windows.iterator();
	        String[] winNames=new String[windows.size()];
	        int i=0;
	        while (iter.hasNext()) {
	            winNames[i]=iter.next();
	            i++;
	        }

	        if(winNames.length > 1) {
	            for(i = 0; i < winNames.length-1; i++) {
	            	driver.switchTo().window(winNames[i]);
	            	driver.close();
	            }
	        }
	        driver.switchTo().window(winNames[winNames.length-1]);
	    }
	    catch(Exception e){         
	        e.printStackTrace();
	    }
	}
	public static WebDriver getDriver() {
		File addonpath = new File(System.getProperty("user.dir") + "\\Adblock_CRX\\Adblock-Plus_v3.1.crx");
		String browser = System.getProperty("browser");
		if (browser == null)
			browser = "chrome";
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("profile.default_content_settings.popups", 0);
//			ChromeOptions options = new ChromeOptions();
//			if (System.getProperty("proxy") != null) {
//				// options.addArguments("--proxy-server=85.172.60.112:80");
//				options.addArguments("--proxy-server=194.226.88.58:3128");
//			}
//			if (siteName == null) {
//				siteName = "www.maskinbladet.dk";
//			}
//			if (siteName.equalsIgnoreCase("tuttocarrellielevatori.it")) {
//				options.addArguments("--proxy-server=182.73.168.68:32088");
//			}
//			if (siteName != null) {
//				if (siteName.equalsIgnoreCase("www.maskinbladet.dk"))
//					options.addArguments("--disable-extensions");
//				else {
//					options.addExtensions(addonpath);
//				}
//				if (siteName.equalsIgnoreCase("agronetsl") || siteName.equalsIgnoreCase("mobile.de")
//						|| siteName.equalsIgnoreCase("tractorpool")) {
//					options.addExtensions(addonpath);
//					addonpath = null;
//					addonpath = new File(System.getProperty("user.dir") + "\\Adblock_CRX\\UltraSurf.crx");
//				//	options.addExtensions(addonpath);
//				}
//			} else
//				options.addExtensions(addonpath);
//			
//			options.addArguments("--test-type");
//			options.addArguments("start-maximized");
//			options.addArguments("--log-level=OFF");
//			options.addArguments("--disable-logging");
//			options.addArguments("disable-popup-blocking");
//			
//	
//			options.setExperimentalOption("prefs", prefs);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("chrome.verbose", false);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		//	capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			WebDriver driver = new ChromeDriver(capabilities);
			waitTill(5000);
			switchTab(driver);
			return driver;
		} else {
			return null;
		}
	}
	public static DesiredCapabilities addProxyCapabilities(DesiredCapabilities capability, String httpProxy) {
		Proxy proxy = new Proxy();
		proxy.setProxyType(ProxyType.MANUAL);
		proxy.setHttpProxy(httpProxy);
		proxy.setSslProxy(httpProxy);
		proxy.setFtpProxy(httpProxy);
		capability.setCapability(CapabilityType.PROXY, proxy);
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return capability;
	}

	public static void maximize(WebDriver driver) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenResolution = new Dimension((int) toolkit.getScreenSize().getWidth(),
				(int) toolkit.getScreenSize().getHeight());
		System.out.println(toolkit.getScreenSize().getWidth() + "---" + toolkit.getScreenSize().getHeight());
		driver.manage().window().setSize(screenResolution);
	}

	public static void jSClick(WebDriver driver, By by) {
		WebElement ele = getWebElement(driver, by);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
	}

	public static void jSClick(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
	}

	public static void waitTill(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
		}
	}

	public static void waitTill() {
		waitTill(3000);
	}

	@SuppressWarnings("deprecation")
	public static void storeErrorLog(String site, String errorMsg) {
		File file = new File("D:\\ErrorLog.txt");
		String errorString = "Error : " + site + "\r\n";
		errorString += errorMsg + "\r\n";
		try {
			FileUtils.writeStringToFile(file, errorString, true);
		} catch (IOException e) {
			System.err.println("Error while storing error log : " + e.getMessage());
		}
	}

	public static void storeExecutionStatusInFile(String fileName, String catID, String PageNum, String suCatID) {
		try {
			Properties properties = new Properties();
			properties.setProperty("catID", catID);
			properties.setProperty("pageNum", PageNum);
			properties.setProperty("suCatID", suCatID);
			File file = new File("D:\\" + fileName + ".properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "Execution Status..");
			fileOut.close();
		} catch (Exception e) {
			System.err.println("System can not find file to get execution status");
		}
	}

	public static void storeExecutionStatusInFile(String fileName, String catID, String PageNum, String make,
			String year, String price) {
		try {
			Properties properties = new Properties();
			properties.setProperty("catID", catID);
			properties.setProperty("pageNum", PageNum);
			properties.setProperty("make", make);
			properties.setProperty("year", year);
			properties.setProperty("price", price);
			File file = new File("D:\\" + fileName + ".properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "Execution Status..");
			fileOut.close();
		} catch (Exception e) {
			System.err.println("System can not find file to get execution status");
		}
	}

	public static Properties openFileToRead(String fileName) {
		Properties properties = new Properties();
		try {
			File file = new File("D:\\" + fileName + ".properties");
			FileInputStream fileInput = new FileInputStream(file);
			properties.load(fileInput);
			fileInput.close();
			return properties;
		} catch (Exception e) {
			System.err.println("System can not open  file to get execution status");
			return properties;
		}

	}

	public static boolean isPriceChanged(String prevPrice, String curPrice) {
		prevPrice = prevPrice.replaceAll("[^0-9]", "");
		curPrice = curPrice.replaceAll("[^0-9]", "");
		boolean status = curPrice.equals(prevPrice);
		if (status) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isCurrencyChanged(String prevPrice, String curPrice) {
		prevPrice = validateCurrency(prevPrice);
		curPrice = validateCurrency(curPrice);
		String prevCurrency = currencyType(prevPrice);
		String curCurrency = currencyType(curPrice);
		boolean status = prevCurrency.equals(curCurrency);
		if (status) {
			return false;
		} else {
			return true;
		}
	}

	public static String validateCurrency(String price) {
		if (price.contains("€")) {
			price = price.replace("€", "EUR");
		} else if (price.contains("$")) {
			price = price.replace("$", "USD");
		} else if (price.contains("грн.")) {
			price = price.replace("грн.", "UAH");
		} else if (price.contains("грн")) {
			price = price.replace("грн", "UAH");
		} else if (price.contains("zł")) {
			price = price.replace("zł", "PLN");
		} else if (price.contains("£")) {
			price = price.replace("£", "GBP");
		}
		price = price.replace("excl. BTW", "").trim();
		price = price.replace("Договорная", "").trim();
		price = price.replace("Обмен", "").trim();
		if (price.replaceAll("[^0-9]", "").isEmpty()) {
			price = "ON REQUEST";
		}
		return price;
	}

	public static String currencyType(String price) {
		String currency = "";
		if (price.contains("PLN")) {
			currency = "PLN";
		} else if (price.contains("EUR")) {
			currency = "EUR";
		} else if (price.contains("RON")) {
			currency = "RON";
		} else if (price.contains("UAH")) {
			currency = "UAH";
		} else if (price.contains("USD")) {
			currency = "USD";
		} else if (price.contains("Ruble")) {
			currency = "RUB";
		} else if (price.contains("SEK")) {
			currency = "SEK";
		} else if (price.contains("GBP")) {
			currency = "GBP";
		}
		return currency;
	}

	@SuppressWarnings("deprecation")
	public static void storeEmailsforNotFoundUnreadessagesLog(String Email) {
		File file = new File("D:\\EmailsforNotFoundUnreadessages.txt");
		String errorString = "No unread messages for this User : " + Email + "\r\n";
		try {
			FileUtils.writeStringToFile(file, errorString, true);
		} catch (IOException e) {
			System.err.println("Error while storing error log : " + e.getMessage());
		}
	}

	public static String[] getMakeAndModel(String title) {
		String[] makeModel = { "", "" };
		for (String ms : Makes) {
			String TempModel = title;
			if (ms.length() > 3) {
				ms = ms.toLowerCase().trim();
				TempModel = TempModel.toLowerCase().trim();
			}
			if (TempModel.startsWith(ms)) {
				if (ms.length() > 3) {
					makeModel[0] = getStringWithCamelCase(ms);
				} else {
					makeModel[0] = ms;
				}
				makeModel[1] = title.substring(makeModel[0].length()).trim();
				break;
			}
		}
		return makeModel;
	}

	public static String getMakeFromAnywhereInString(String makeString) {
		String make = "";
		for (String ms : Makes) {
			String TempModel = makeString;
			for (String minString : TempModel.split(" ")) {
				if (minString.length() > 3) {
					ms = ms.toLowerCase().trim();
					minString = minString.toLowerCase().trim();
				}
				if (minString.equalsIgnoreCase(ms)) {
					if (ms.length() > 3) {
						make = getStringWithCamelCase(ms);
					} else {
						make = ms;
					}

				}
				if (!make.isEmpty())
					break;
			}
			if (!make.isEmpty())
				break;
		}
		return make;
	}

	public static boolean checkTimeToRunScripts() throws Exception {
		boolean condition = true;
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		SimpleDateFormat dateFormatNew = new SimpleDateFormat("hh a");
		dateFormat.format(date);
		dateFormatNew.format(date);
		String stopTime = "12:30 AM";
		String startTime = "10:30 AM";
		String middleTime = "04 AM";
		if (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse(stopTime))
				&& dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse(startTime))) {
			System.out.println("Time Is In Between 8 PM To 6 AM In Lisbon, Stopping The Scripts....!");
			condition = false;
		}
		if (dateFormatNew.parse(dateFormatNew.format(date)).equals(dateFormatNew.parse(middleTime))) {
			System.out.println("Time Is 12 AM In Lisbon, Starting Script For One Time....!");
			condition = true;
		}
		return condition;
	}

	public static void sendMail(String subject, String content, String receivers) throws Exception {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session1 = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("test2@sunfra.com", "sunfra@123");
				}
			});
			Message message = new MimeMessage(session1);
			message.setFrom(new InternetAddress("test2@sunfra.com", "OfferCount"));
			String recipient = receivers;
			String[] recipientList = recipient.split(",");
			InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
			int counter = 0;
			for (String recipient2 : recipientList) {
				recipientAddress[counter] = new InternetAddress(recipient2.trim());
				counter++;
			}
			message.setRecipients(Message.RecipientType.TO, recipientAddress);

			message.setSubject(subject);
			message.setContent(content, "text/html; charset=utf-8");
			message.setText(content);
			message.setContent(content, "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Mail Sent Successfully....!");

		} catch (

		Exception e) {
			System.out.println("Issue while sending Build Status");
			e.printStackTrace();
		}
	}

	public static void sendMailWithCC(String subject, String content, String receivers, String recipientToCC)
			throws Exception {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("test2@sunfra.com", "sunfra@123");
				}
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("test2@sunfra.com", "Tradus Investigation"));
			String recipient = receivers;
			String[] recipientList = recipient.split(",");
			InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
			int counter = 0;
			for (String recipient2 : recipientList) {
				recipientAddress[counter] = new InternetAddress(recipient2.trim());
				counter++;
			}
			message.setRecipients(Message.RecipientType.TO, recipientAddress);

			String[] recipientListForCC = recipientToCC.split(",");
			InternetAddress[] recipientAddressforCC = new InternetAddress[recipientListForCC.length];
			counter = 0;
			for (String recipient2 : recipientListForCC) {
				recipientAddressforCC[counter] = new InternetAddress(recipient2.trim());
				counter++;
			}
			message.setRecipients(Message.RecipientType.CC, recipientAddressforCC);

			message.setSubject(subject);
			message.setContent(content, "text/html; charset=utf-8");
			message.setText(content);
			message.setContent(content, "text/html; charset=utf-8");
			Transport.send(message);
			System.out.println("Mail Sent Successfully....!");

		} catch (

		Exception e) {
			System.out.println("Issue while sending Build Status" + e);
		}
	}

	public static String getCurrentDateAndTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String CurrenDateAndTime = dateFormat.format(date);
		return CurrenDateAndTime;
	}
	public static void csvFile(String FileName,String data) throws IOException{
		String rootDir = System.getProperty("user.home") +"\\Desktop\\CSV\\hello\\";
		File fila= new File(rootDir);
		if (!fila.isDirectory()) {
			fila.mkdirs();
		}
		if(FileName.contains(".")){
			FileName=FileName.replaceAll("\\.", "_");
		}
		File file = new File(rootDir + FileName + ".csv");
		if (file.exists())
			{
			
			}
		else
			try{
			file.createNewFile();
			}catch (Exception e) {
			}
		try {
			File newTextFile = new File(rootDir + FileName + ".csv");
			String content = new String(Files.readAllBytes(Paths.get(newTextFile.toString())));
			/*if (content.contains(data)) {
				System.out.println("Data already in file: " + data);
			} else {*/
				FileWriter fw = new FileWriter(newTextFile, true);
				fw.write(data + "\r\n");
				fw.close();
				
			/*}*/


		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}
}

