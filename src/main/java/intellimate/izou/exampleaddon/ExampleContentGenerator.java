package intellimate.izou.exampleaddon;

import intellimate.izou.contentgenerator.ContentData;
import intellimate.izou.contentgenerator.ContentGenerator;

/**
 * Created by julianbrendl on 10/24/14.
 */
public class ExampleContentGenerator extends ContentGenerator<ExampleFetchedData> {
    /*
    ID consists of class_name
     */
    private static final String ID = ExampleContentGenerator.class.getCanonicalName();

    public ExampleContentGenerator() {
        super(ID);
    }

    @Override
    public ContentData generate(String s) throws Exception {
        String contentDataID = "example";
        ContentData<ExampleFetchedData> contentData = new ContentData<>(contentDataID);
        contentData.setData(new ExampleFetchedData("Hello World!"));
        return contentData;
    }

    @Override
    public void handleError(Exception e) {

    }
}
