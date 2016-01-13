package shared;

/**
 * Created by SAEED on 2016-01-07
 * for project BusinessSearchEngine .
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * global Parameters within project is here
 */
public class Params {
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy/mm/dd";

    public static final String PATH_EXCEL = ".\\.\\outputExcel\\fetchedInformation.xlsx";
    public static final String PATH_TEMP_STORAGE = ".\\.\\tempStorage";
    public static final String PATH_INDEX = ".\\.\\outputIndex";

    public static final int POLITENESS_DELAY = 200;
    public static final int MAX_DEPTH_OF_CRAWLING = -1;
    public static final int MAX_PAGES_TO_FETCH = -1;

    public static final int SHEET_COUNT = 20;

    public static final int SHEET_Jobcity = 0;
    public static final int SHEET_ESHETAB = 1;
    public static final int SHEET_KARYABI_SAZANDEGI = 2;
    public static final int SHEET_NIAZMA = 3;
    public static final int SHEET_IRANKAR = 4;
    public static final int SHEET_DLPA = 5;
    public static final int SHEET_KARAN = 6;
    public static final int SHEET_PNUNA = 7;
    public static final int SHEET_IRAN_TEJARAT = 8;
    public static final int SHEET_PARDAD = 9;
    public static final int SHEET_AFTABIR = 10;
    public static final int SHEET_ZANJANKAR = 11;
    public static final int SHEET_ESTEKHDAM_KABAR = 12;
    public static final int SHEET_MEHRJOB = 13;
    public static final int SHEET_BAROOT = 14;
    public static final int SHEET_LASTJOB = 15;
    public static final int SHEET_DONYAYEKAR = 16;
    public static final int SHEET_UNP = 17;
    public static final int SHEET_SABAINFO = 18;
    public static final int SHEET_ECITY24 = 19;
    public static final int SHEET_GOODCO = 20;
    public static final int SHEET_ESTEKHDAMIA = 21;
    public static final int SHEET_ESTEKHDAM24 = 22;

    public static final String FIELD_TITLE = "title";
    public static final String FIELD_BODY = "body";
    public static final String FIELD_CITY = "city";
    public static final String FIELD_DATE = "date";

    public static ArrayList<String> citiesList = new ArrayList<String>(Arrays.asList(
            "اسكو",
            "بستان آباد",
            "بناب",
            "بندر شرفخانه",
            "تبريز",
            "تسوج",
            "جلفا",
            "سراب",
            "شبستر",
            "صوفیان",
            "عجبشير",
            "قره آغاج",
            "كليبر",
            "كندوان",
            "مراغه","مرند",
            "ملكان",
            "ممقان",
            "ميانه",
            "هاديشهر",
            "هريس",
            "هشترود",
            "ورزقان",
            "اروميه",
            "اشنويه",
            "بوكان",
            "پلدشت",
            "پيرانشهر",
            "تكاب",
            "خوي",
            "سردشت",
            "سلماس",
            "سيه چشمه- چالدران",
            "سیمینه",
            "شاهين دژ",
            "شوط",
            "ماكو",
            "مهاباد",
            "مياندوآب",
            "نقده",
            "اردبيل",
            "بيله سوار",
            "پارس آباد",
            "خلخال",
            "سرعين",
            "كيوي (كوثر)",
            "گرمي (مغان)",
            "مشگين شهر",
            "مغان (سمنان)",
            "نمين",
            "نير",
            "آران و بيدگل",
            "اردستان",
            "اصفهان",
            "باغ بهادران",
            "تيران",
            "خميني شهر",
            "خوانسار",
            "دهاقان",
            "زرين شهر",
            "زيباشهر (محمديه)",
            "سميرم",
            "شاهين شهر",
            "شهرضا",
            "فريدن",
            "فريدون شهر",
            "فلاورجان",
            "فولاد شهر",
            "قهدریجان",
            "كاشان",
            "گلپايگان",
            "گلدشت اصفهان",
            "گلدشت مركزی",
            "نايين",
            "نجف آباد",
            "نطنز",
            "هرند",
            "آسارا",
            "اشتهارد",
            "شهر جديد هشتگرد",
            "طالقان",
            "كرج",
            "گلستان تهران",
            "نظرآباد",
            "هشتگرد",
            "آبدانان",
            "ایلام",
            "ايوان",
            "دره شهر",
            "دهلران",
            "سرابله",
            "شيروان چرداول",
            "مهران",
            "آبپخش",
            "اهرم",
            "برازجان",
            "بندر دير",
            "بندر ديلم",
            "بندر كنگان",
            "بندر گناوه",
            "بوشهر",
            "تنگستان",
            "جزيره خارك",
            "جم (ولايت)",
            "خورموج",
            "دشتستان - شبانکاره",
            "دلوار",
            "عسلویه",
            "اسلامشهر",
            "بومهن",
            "پاكدشت",
            "تهران",
            "چهاردانگه",
            "دماوند",
            "رودهن",
            "ري",
            "شريف آباد",
            "شهر رباط كريم",
            "شهر شهريار",
            "فشم",
            "فيروزكوه",
            "كهريزك",
            "لواسان بزرگ",
            "ملارد",
            "ورامين",
            "اردل",
            "بروجن",
            "چلگرد (كوهرنگ)",
            "سامان",
            "شهركرد",
            "فارسان",
            "لردگان",
            "بشرویه",
            "بيرجند",
            "خضری",
            "خوسف",
            "سرایان",
            "سربيشه",
            "طبس",
            "فردوس",
            "قائن",
            "نهبندان",
            "بجستان",
            "بردسكن",
            "تايباد",
            "تربت جام",
            "تربت حيدريه",
            "جغتای",
            "جوین",
            "چناران",
            "خلیل آباد",
            "خواف",
            "درگز",
            "رشتخوار",
            "سبزوار",
            "سرخس",
            "طبس",
            "طرقبه",
            "فريمان",
            "قوچان",
            "كاشمر",
            "كلات",
            "گناباد",
            "مشهد",
            "نيشابور",
            "آشخانه، مانه و سمرقان",
            "اسفراين",
            "بجنورد",
            "جاجرم",
            "شيروان",
            "فاروج",
            "آبادان",
            "اميديه",
            "انديمشك",
            "اهواز",
            "ايذه",
            "باغ ملك",
            "بستان",
            "بندر ماهشهر",
            "بندرامام خميني",
            "بهبهان",
            "خرمشهر",
            "دزفول",
            "رامشیر",
            "رامهرمز",
            "سوسنگرد",
            "شادگان",
            "شوش",
            "شوشتر",
            "لالي",
            "مسجد سليمان",
            "هنديجان",
            "هويزه",
            "آب بر (طارم)",
            "ابهر",
            "خرمدره",
            "زرین آباد (ایجرود)",
            "زنجان",
            "قیدار (خدا بنده)",
            "ماهنشان",
            "ايوانكي",
            "بسطام",
            "دامغان",
            "سرخه",
            "سمنان",
            "شاهرود",
            "شهمیرزاد",
            "گرمسار",
            "مهدیشهر",
            "ايرانشهر",
            "چابهار",
            "خاش",
            "راسك",
            "زابل",
            "زاهدان",
            "سراوان",
            "سرباز",
            "ميرجاوه",
            "نيكشهر",
            "آباده",
            "آباده طشك",
            "اردكان",
            "ارسنجان",
            "استهبان",
            "اشكنان",
            "اقليد",
            "اوز",
            "ایج",
            "ایزد خواست",
            "یزد",
            "باب انار",
            "بالاده",
            "بنارويه",
            "بهمن",
            "بوانات",
            "بيرم",
            "بیضا",
            "جنت شهر",
            "جهرم",
            "حاجي آباد-زرین دشت",
            "خاوران",
            "خرامه",
            "خشت",
            "خفر",
            "خنج",
            "خور",
            "داراب",
            "رونيز عليا",
            "زاهدشهر",
            "زرقان",
            "سده",
            "سروستان",
            "سعادت شهر",
            "سورمق",
            "ششده",
            "شيراز",
            "صغاد",
            "صفاشهر",
            "علاء مرودشت",
            "عنبر",
            "فراشبند",
            "فسا",
            "فيروز آباد",
            "قائميه",
            "قادر آباد",
            "قطب آباد",
            "قير",
            "كازرون",
            "كنار تخته",
            "گراش",
            "لار",
            "لامرد",
            "لپوئی",
            "لطيفي",
            "مبارك آباد ديز",
            "مرودشت",
            "مشكان",
            "مصير",
            "مهر فارس(گله دار)",
            "ميمند",
            "نوبندگان",
            "نودان",
            "نورآباد",
            "ني ريز",
            "کوار",
            "آبيك",
            "البرز",
            "بوئين زهرا",
            "تاكستان",
            "قزوين",
            "محمود آباد نمونه",
            "قم",
            "بانه",
            "بيجار",
            "دهگلان",
            "ديواندره",
            "سقز",
            "سنندج",
            "قروه",
            "كامياران",
            "مريوان",
            "بابك",
            "بافت",
            "بردسير",
            "بم",
            "جيرفت",
            "راور",
            "رفسنجان",
            "زرند",
            "سيرجان",
            "كرمان",
            "كهنوج",
            "منوجان",
            "اسلام آباد غرب",
            "پاوه",
            "تازه آباد- ثلاث باباجانی",
            "جوانرود",
            "سر پل ذهاب",
            "سنقر كليائي",
            "صحنه",
            "قصر شيرين",
            "كرمانشاه",
            "كنگاور",
            "گيلان غرب",
            "هرسين",
            "دهدشت",
            "دوگنبدان",
            "سي سخت- دنا",
            "گچساران",
            "ياسوج",
            "آزاد شهر",
            "آق قلا",
            "انبارآلوم",
            "اینچه برون",
            "بندر گز",
            "تركمن",
            "جلین",
            "خان ببین",
            "راميان",
            "سرخس کلاته",
            "سیمین شهر",
            "علي آباد كتول",
            "فاضل آباد",
            "كردكوي",
            "كلاله",
            "گالیکش",
            "گرگان",
            "گمیش تپه",
            "گنبد كاووس",
            "مراوه تپه",
            "مينو دشت",
            "نگین شهر",
            "نوده خاندوز",
            "نوکنده",
            "آستارا",
            "آستانه اشرفيه",
            "املش",
            "بندرانزلي",
            "خمام",
            "رشت",
            "گیلان",
            "رضوان شهر",
            "رود سر",
            "رودبار",
            "سياهكل",
            "شفت",
            "صومعه سرا",
            "فومن",
            "كلاچاي",
            "لاهيجان",
            "لنگرود",
            "لوشان",
            "ماسال",
            "ماسوله",
            "منجيل",
            "هشتپر",
            "ازنا",
            "الشتر",
            "اليگودرز",
            "بروجرد",
            "پلدختر",
            "خرم آباد",
            "دورود",
            "سپید دشت",
            "كوهدشت",
            "نورآباد (خوزستان)",
            "آمل",
            "بابل",
            "بابلسر",
            "بلده",
            "بهشهر",
            "پل سفيد",
            "تنكابن",
            "جويبار",
            "چالوس",
            "خرم آباد",
            "رامسر",
            "رستم کلا",
            "ساري",
            "سلمانشهر",
            "سواد كوه",
            "فريدون كنار",
            "قائم شهر",
            "گلوگاه",
            "محمودآباد",
            "مرزن آباد",
            "نكا",
            "نوشهر",
            "آشتيان",
            "اراك",
            "تفرش",
            "خمين",
            "دليجان",
            "ساوه",
            "شازند",
            "محلات",
            "کمیجان",
            "ابوموسي",
            "انگهران",
            "بستك",
            "بندر جاسك",
            "بندر لنگه",
            "بندرعباس",
            "پارسیان",
            "حاجي آباد",
            "دشتی",
            "دهبارز (رودان)",
            "قشم",
            "كيش",
            "ميناب",
            "اسدآباد",
            "بهار",
            "تويسركان",
            "رزن",
            "كبودر اهنگ",
            "ملاير",
            "نهاوند",
            "همدان",
            "ابركوه",
            "اردكان",
            "اشكذر",
            "بافق",
            "تفت",
            "مهريز",
            "ميبد",
            "هرات",
            "آذربايجان شرقي",
            "آذربايجان غربي",
            "اردبيل",
            "اصفهان",
            "البرز",
            "ايلام",
            "بوشهر",
            "تهران",
            "چهارمحال بختياري",
            "خراسان جنوبي",
            "خراسان رضوي",
            "خراسان شمالي",
            "خوزستان",
            "زنجان",
            "سمنان",
            "سيستان و بلوچستان",
            "فارس",
            "قزوين",
            "قم",
            "كردستان",
            "كرمان",
            "كرمانشاه",
            "كهكيلويه و بويراحمد",
            "گلستان",
            "گيلان",
            "لرستان",
            "مازندران",
            "مركزي",
            "هرمزگان",
            "همدان",
            "آذر شهر"
    ));

    public static ArrayList<String> citiesListEnglish = new ArrayList<String>(Arrays.asList(
            "osku",
            "bostanabad",
            "therefore",
            "port sharafkhaneh",
            "tabriz",
            "tasuj",
            "julfa",
            "mirage",
            "shabestar",
            "sufi",
            "farmers",
            "ghareaghaj",
            "kleiber",
            "hive",
            "maragha",
            "marand",
            "malekan",
            "mamaghan",
            "median",
            "hadishahr",
            "harris",
            "hashtrood",
            "varzeghan",
            "urmia",
            "oshnavieh",
            "bokan",
            "poldasht",
            "piranshar",
            "tekab",
            "disposition",
            "exposed",
            "salmas",
            "siah cheshmeh chalderan",
            "simineh",
            "shahindezh",
            "round",
            "maku",
            "mahabad",
            "mentioned",
            "tinsel",
            "ardabil",
            "bilesavar",
            "parsabad",
            "anklet",
            "sareyn",
            "kiwi (kosar)",
            "grammy (magi)",
            "meshkinshar",
            "taha (semnan)",
            "namin",
            "nir",
            "aran",
            "ardestan",
            "esfahan",
            "baghbahadoran",
            "tiran",
            "khomeini shahr",
            "khansar",
            "dehaghan",
            "golden city",
            "zibashahr (muhammadiyah)",
            "semirom",
            "shahinshahr",
            "branch",
            "frieden",
            "f city",
            "branch",
            "steel city",
            "qahderijan",
            "yes",
            "golpaygan",
            "goldasht",
            "goldasht center",
            "nain",
            "najaf abad",
            "natanz",
            "harand",
            "asara",
            "eshtehard",
            "the city of new hashtgerd",
            "taleghan",
            "karaj",
            "golestan tehran",
            "nazarabad",
            "hashtgerd",
            "abdanan",
            "elam",
            "ivan",
            "canyon city",
            "dehloran",
            "sarableh",
            "shirvanchardavol",
            "m",
            "abpakhsh",
            "lever",
            "okapi",
            "port late",
            "port deilam",
            "bandar kangan",
            "port ganaveh",
            "bushehr",
            "tangistan",
            "kharg island",
            "jam (province)",
            "khormoj",
            "dashtestan - shabankareh",
            "delaware",
            "assaluyeh",
            "branch",
            "boomehen",
            "pakdasht",
            "tehran",
            "chahardangeh",
            "damavand",
            "roodehen",
            "ray",
            "sharifabad",
            "city robat karim",
            "the town of shahriar",
            "fasham",
            "firouzkouh",
            "kahrizak",
            "great lavasan",
            "mallard",
            "vermin",
            "ardal",
            "borujen",
            "chelgerd (kouhrang)",
            "saman",
            "kord",
            "persian",
            "lordegan",
            "boshrooyeh",
            "birjand",
            "khezri",
            "khoosf",
            "chorus",
            "sarbishe",
            "tops",
            "paradise",
            "cain",
            "nehbandan",
            "bajestan",
            "bardaskan",
            "taybad",
            "torbat",
            "torbat",
            "chaghatay",
            "jovin",
            "sycamore",
            "khalil abad",
            "khaf",
            "dargaz",
            "roshtkhar",
            "sabzevar",
            "fern",
            "tops",
            "torghabeh",
            "frame",
            "ghochan",
            "kashmar",
            "chelate",
            "yes",
            "mashhad",
            "nishapur",
            "ashkhaneh, manet and smrqan",
            "esfaraien",
            "bojnoord",
            "jajarm",
            "shirvan",
            "faruj",
            "abadan",
            "omīdīyeh",
            "andimeshk",
            "ahvaz",
            "izeh",
            "gardens of the king",
            "take",
            "mahshahr",
            "imam khomeini",
            "pa",
            "khorramshahr",
            "dezful",
            "ramshir",
            "rāmhormoz",
            "susangerd",
            "shadegan",
            "shush",
            "shushtar",
            "lali",
            "masjed soleiman",
            "hendijan",
            "otitis media",
            "water on (tarom)",
            "abhar",
            "khoramdeh",
            "zarinabad (ijrud)",
            "zanjan",
            "kedar (god's servant)",
            "mahneshan",
            "ivanaki",
            "bastam",
            "damghan",
            "sorkheh",
            "semnan",
            "anymore",
            "shahmirzad",
            "branch",
            "mehdishahr",
            "iranshahr",
            "chabahar",
            "khash",
            "rask",
            "zabul",
            "zahedan",
            "saravan",
            "soldier",
            "mirjaveh",
            "nikshahr",
            "abade",
            "abade tashk",
            "duck",
            "arsanjan",
            "estahban",
            "ashcan",
            "eghlid",
            "oz",
            "age",
            "god willing",
            "yazd",
            "the pomegranate",
            "baladeh",
            "banaruiyeh",
            "avalanche",
            "bavanat",
            "bayram",
            "bayda",
            "paradise city",
            "arranged",
            "haji abad-golden plains",
            "grave",
            "kharameh",
            "clay",
            "khafr",
            "khonj",
            "estuary",
            "d.",
            "roniz upper",
            "zahedshahr",
            "zarghan",
            "century",
            "sarvestan",
            "the welfare of the city",
            "surmagh",
            "sheshdeh",
            "shiraz",
            "soghad",
            "safashahr",
            "alaa shiraz.",
            "amber",
            "farashband",
            "fasa",
            "firoozabad",
            "ghaemieh",
            "ghaderabad",
            "ghotbabadi",
            "bitumen",
            "shiraz",
            "the board",
            "bike",
            "lar",
            "lamard",
            "lapo",
            "subtle",
            "mobarakabad diez",
            "wheat",
            "meshkan",
            "masiri",
            "seal gulf (rancher)",
            "meimand",
            "nvbndgan",
            "nodan",
            "nour",
            "neyriz",
            "kovar",
            "abyek",
            "alborz",
            "buin zahra",
            "vineyard",
            "qazvin",
            "mahmoud abad sample",
            "qom",
            "bane",
            "bijar",
            "dehgolan",
            "divandareh",
            "turpentine",
            "sanandaj",
            "qorveh",
            "kamyaran",
            "marivan",
            "b.",
            "texture",
            "bardsir",
            "bam",
            "jiroft",
            "raver",
            "rafsanjan",
            "zarand",
            "sirjan",
            "kerman",
            "kahnooj",
            "manoujan",
            "west pakistan",
            "da",
            "new abad salas babajani",
            "peers",
            "sarpolezahab",
            "falcon koliaie",
            "scene",
            "qasr-e shirin",
            "kermanshah",
            "kangavar",
            "gilan west",
            "harsin",
            "dehdasht",
            "dogonbadan",
            "thirty hard-dena",
            "traditional",
            "yasouj",
            "free city",
            "agh ghala",
            "anbar olum",
            "incheboron",
            "bandar gaz",
            "turkmen",
            "jlyn",
            "look khan",
            "ramian",
            "fern chelated",
            "silver city",
            "aliabad katool",
            "fazelabad",
            "korkoy",
            "stigma",
            "galikesh",
            "gorgan",
            "gomishan hill",
            "dome kavoos",
            "maravetappe",
            "minu plain",
            "pearl city",
            "now deh khanduz",
            "now kandeh",
            "astara",
            "baste",
            "amlash",
            "bandar anzali",
            "khmam",
            "city",
            "gilan",
            "rezvan city",
            "the head",
            "roodbar",
            "siahkal",
            "shaft",
            "sara",
            "appreciable",
            "kelachay",
            "lahijan",
            "langrood",
            "shan",
            "masal",
            "village",
            "manjil",
            "hashtpar",
            "azna",
            "elster",
            "aligoudarz",
            "borujerd",
            "poldokhtar",
            "khorramabad",
            "dorud",
            "white plains",
            "koohdasht",
            "nurabad (khuzestan)",
            "amul",
            "babel",
            "babolsar",
            "knows",
            "to city",
            "white bridge",
            "tonkabon",
            "stream",
            "branch",
            "khorramabad",
            "ramsar",
            "rostam kola",
            "sari",
            "salman shahr",
            "savadkooh",
            "fereidoonkenar",
            "vice city",
            "bottleneck",
            "mahmoud",
            "marzanabad",
            "neka",
            "branch",
            "reconciliation",
            "arak",
            "tafresh",
            "khomeini",
            "stagecoach",
            "save",
            "mentioned",
            "neighborhoods",
            "komijan",
            "abu musa",
            "angohrān",
            "block",
            "bandar jask",
            "lengeh seaport",
            "bandar",
            "corp",
            "haji abad",
            "plain",
            "dhbarz (mesopotamia)",
            "island",
            "kish",
            "minab",
            "asadabad",
            "spring",
            "tuyserkan",
            "razan",
            "kaboudarahang",
            "malayer",
            "skinheads",
            "hamadan",
            "abarkouh",
            "duck",
            "ashkezar",
            "valleys",
            "taft",
            "mehriz",
            "meibod",
            "herat",
            "east azerbaijan",
            "west azerbaijan",
            "ardabil",
            "esfahan",
            "alborz",
            "elam",
            "bushehr",
            "tehran",
            "bakhtiari",
            "south khorasan",
            "khorasan",
            "north khorasan",
            "khuzestan",
            "zanjan",
            "semnan",
            "sistan and baluchistan",
            "persian",
            "qazvin",
            "qom",
            "kurdistan",
            "kerman",
            "kermanshah",
            "kohgiluyeh and boyer",
            "gulistan",
            "gilan",
            "lorestan",
            "mazandaran",
            "central",
            "hormozgan",
            "hamadan",
            "azarshahr"
    ));
}
