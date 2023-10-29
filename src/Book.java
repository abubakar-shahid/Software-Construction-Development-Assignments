public class Book {

    protected static int nextID = 1;
    protected int id;
    protected String title;
    protected int type;
    protected Borrower borrower;
    protected int popularityCount;
    protected int cost;
    protected String author;
    protected int year;
    protected String content;

    //------------------------------------------------------------------------------------------------------------------------------------------
    public Book(String title, String auth, int year, int flag, int count, int cost) {
        if (flag == 0) {
            this.id = Book.nextID++;
        } else {
            this.id = flag;
        }
        this.title = title;
        this.author = auth;
        this.year = year;
        this.type = 1;
        this.popularityCount = count;
        this.borrower = null;
        this.cost = cost;
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vel semper ante. Donec aliquam hendrerit dui, at accumsan urna tristique ut. Pellentesque eget odio vel libero consectetur iaculis. Sed fringilla vel sapien in lacinia. Nullam non erat id purus viverra tempor. Proin auctor, nisl eu condimentum lacinia, ex justo tempus nisi, vel aliquet elit justo vel lectus. Sed varius viverra eros, eu tristique ex. Curabitur id condimentum erat. Vivamus bibendum ex sit amet justo eleifend, nec gravida sapien bibendum. Nullam nec erat id lectus dignissim pellentesque. Cras ut urna justo." +
                "Suspendisse laoreet mi sit amet ipsum laoreet, nec semper metus eleifend. Maecenas fringilla nisl nec ex consequat, vel lacinia odio cursus. In hac habitasse platea dictumst. In fringilla eleifend nisl, id volutpat felis bibendum at. Curabitur sit amet fringilla justo. Nam bibendum ac tortor in interdum. Sed quis urna eget metus sagittis varius. Sed egestas, tortor id dictum laoreet, ligula eros tempus justo, et lacinia purus ipsum nec orci." +
                "Vestibulum pellentesque in lorem non lacinia. Suspendisse potenti. Sed fermentum, odio vel volutpat pellentesque, ex massa dictum sem, ut vehicula justo purus ut dolor. Curabitur ullamcorper, libero nec ultrices fringilla, urna justo dapibus turpis, at scelerisque justo lectus ac dolor. Sed eu nunc orci. In ac posuere elit. Morbi vestibulum risus ut dolor elementum, at varius tellus accumsan. Quisque euismod nec leo nec venenatis." +
                "Vivamus placerat, dolor ac lacinia dictum, metus tortor feugiat ex, ut condimentum nisl arcu vel quam. Sed sit amet sollicitudin odio. Nullam ac tortor tristique, tempus arcu nec, condimentum purus. Suspendisse eget urna augue. Curabitur tristique, lectus sit amet pharetra malesuada, quam odio condimentum orci, ut luctus metus elit vel justo. Integer semper nisi id justo vehicula, ac scelerisque urna vulputate. Sed nec dapibus neque. Aenean in bibendum libero. Phasellus dapibus, leo et laoreet viverra, ex ante scelerisque justo, sit amet lacinia ligula libero in nisi." +
                "Sed euismod augue eu lectus vehicula, eget egestas felis tristique. Nulla facilisi. Proin in nisi eget nunc feugiat dictum. Morbi efficitur ligula in euismod auctor. Donec varius mi vitae facilisis bibendum. Vestibulum bibendum lacus ac felis suscipit, a hendrerit augue venenatis. In id nunc vestibulum, bibendum mi at, aliquam risus. Aenean in ipsum at lectus lacinia ullamcorper ac eu lorem. Nullam ut odio nec justo tincidunt sollicitudin." +
                "Vivamus a lectus vel quam aliquet mattis a non ante. Curabitur lacinia ex nec tristique. Pellentesque non neque a justo bibendum scelerisque. Sed ac elit eu lectus auctor scelerisque. Fusce auctor, justo in tristique vulputate, urna libero cursus orci, id bibendum justo odio et libero. Suspendisse eget velit in nisl egestas sollicitudin. Nunc vehicula ligula eu sapien laoreet, ac dictum libero vestibulum. Proin sit amet sapien aliquet, euismod quam ut, pharetra mi." +
                "Proin nec sapien vitae ex malesuada gravida. Phasellus ac eros sed quam varius condimentum. Phasellus vehicula, erat vel interdum iaculis, libero libero facilisis tellus, at congue mi augue ac metus. Nulla facilisi. Proin in metus quam. Quisque euismod dui sed augue varius, vel vestibulum nulla vulputate. Nullam vel lectus sed lectus gravida suscipit. Sed eu elit at lectus lacinia lacinia." +
                "Sed nec quam et ex interdum vulputate ac nec dui. Quisque vel tincidunt nunc. Sed ultrices est eget elit vulputate, ac aliquam sem tincidunt. Nullam tempus ante at fringilla sodales. Proin dictum varius metus in venenatis. Curabitur bibendum, turpis et suscipit bibendum, arcu justo vulputate tellus, ac vestibulum justo justo vel quam. Nulla tristique tortor nec libero iaculis bibendum. Vestibulum ullamcorper libero id efficitur. Sed nec venenatis est." +
                "Fusce sed nulla non purus elementum pellentesque. Donec bibendum ac odio eu semper. Suspendisse a metus a lectus suscipit malesuada. Curabitur a metus in ipsum lacinia efficitur. Vivamus sodales nisl ut urna varius, sed vestibulum metus fermentum. Fusce bibendum, mi nec congue laoreet, augue nulla tempor orci, sed elementum lectus lorem at arcu. Maecenas id mauris vel sapien sodales accumsan. Curabitur vel venenatis sapien." +
                "Suspendisse potenti. Donec quis ex sit amet dui vestibulum ullamcorper. Vivamus laoreet odio a nunc laoreet, eu laoreet ante luctus. Sed auctor ligula id purus tempus, id vehicula odio dictum. Curabitur vitae justo nec nulla scelerisque congue ac at sem. Nullam ut tincidunt elit. Integer eu turpis at odio congue scelerisque. Sed euismod ligula non sem eleifend, at tristique quam aliquet. Nulla at elit vitae turpis venenatis aliquet eu a arcu. Proin venenatis nec tortor id iaculis. Integer ac posuere augue." +
                "Vestibulum euismod lorem vel augue bibendum ultrices. Nullam posuere bibendum metus, ac iaculis orci scelerisque nec. Phasellus suscipit elit vel ex scelerisque, sed congue massa hendrerit. Sed in purus sit amet nisl efficitur dictum. Proin interdum metus nec sapien dignissim, vel fringilla d\n";
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    public double calculateCost() {
        return (this.cost + (0.2 * this.cost) + 200);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    public int getPopularityCount() {
        return this.popularityCount;
    }
    //------------------------------------------------------------------------------------------------------------------------------------------

    public String getData() {
        String output = "";
        output += (this.type + ", ");
        output += (this.title + ", ");
        output += (this.author + ", ");
        output += (this.year + ", ");
        output += (this.popularityCount + ", ");
        output += (this.cost + "\n");
        return output;
    }
    //------------------------------------------------------------------------------------------------------------------------------------------
}