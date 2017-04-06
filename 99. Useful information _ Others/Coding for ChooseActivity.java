private final double GREENSCREEN = 1.5;
    private final double CHAIR = 1.9;
    private final double PERSON = 4.73;

    private Boolean youtuber = false;
    private Boolean recordingStudio = false;
    private Boolean event = false;

    private Button youtuberBtn;
    private Button recordingStudioBtn;
    private Button eventBtn;

    private double catResult;
    private double noOfPeople;
    private EditText noOfPeopleET;

    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);



        youtuberBtn = (Button) findViewById(R.id.youtubeBtn);
        youtuberBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                catResult = GREENSCREEN + CHAIR + PERSON;
                youtuber = true;
                recordingStudio = false;
                event = false;
                Next();
            }
        });

        recordingStudioBtn = (Button) findViewById(R.id.recordingStudioBtn);
        recordingStudioBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                catResult = CHAIR + PERSON;
                recordingStudio = true;
                youtuber = false;
                event = false;
                Next();
            }
        });


        eventBtn = (Button) findViewById(R.id.eventBtn);
        eventBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                noOfPeopleET = (EditText) findViewById(R.id.noofPeopleET);
                noOfPeople = Double.parseDouble(noOfPeopleET.toString());

                catResult = PERSON * noOfPeople;
                event = true;
                youtuber = false;
                recordingStudio = false;
                Next();
            }
        
        });

    }
    