package intellimate.izou.exampleaddon;

import intellimate.izou.contentgenerator.ContentData;
import intellimate.izou.output.OutputExtension;

/**
 * Created by julianbrendl on 10/24/14.
 */
public class ExampleOutputExtension extends OutputExtension<ExampleOutputData> {
    /*
    ID consists of class_name
     */
    public static final String ID = ExampleOutputExtension.class.getCanonicalName();

    public ExampleOutputExtension() {
        super(ID);
        addContentDataToWishList("example");    //has to be same as contentData Id
        setPluginId(ExampleOutputPlugin.ID);
    }

    @Override
    public ExampleOutputData call() throws Exception {
        String totalOutput = "";
        for(ContentData cD: this.getContentDataList()) {
            ExampleFetchedData fetchedData = (ExampleFetchedData)cD.getData();
            totalOutput += fetchedData.getData() + ", ";
        }
        totalOutput = totalOutput.substring(0, totalOutput.length() - 2);
        return new ExampleOutputData(totalOutput);
    }
}
