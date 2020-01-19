package valeriamoscoso.ioc.example.data.network.server;


import android.content.Context;

import java.util.concurrent.TimeUnit;

import io.appflate.restmock.MockAnswer;
import io.appflate.restmock.RESTMockServer;
import io.appflate.restmock.RESTMockServerStarter;
import io.appflate.restmock.android.AndroidAssetsFileParser;
import io.appflate.restmock.android.AndroidLogger;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import valeriamoscoso.ioc.hanguldaebak.data.network.ConectionUtils;

import static io.appflate.restmock.utils.RequestMatchers.pathContains;


public class MockServer {

    private static final long DELAY_SEC = 1;
    private final AndroidAssetsFileParser mocksFileParser;

    public MockServer(Context context) {
        mocksFileParser = new AndroidAssetsFileParser(context);
    }

    public void start() {
        RESTMockServerStarter.startSync(mocksFileParser,
                new AndroidLogger());

        RESTMockServer.whenGET(pathContains("/examples"))
                .thenReturnFile(ConectionUtils.CODE_200, "examples.json")
                .delay(TimeUnit.SECONDS, DELAY_SEC);


        RESTMockServer.whenGET(pathContains("api/profile"))
                .thenReturnFile(ConectionUtils.CODE_200, "profile.json")
                .delay(TimeUnit.SECONDS, DELAY_SEC);


//        RESTMockServer.whenPOST(pathContains("important_operation"))
//                .thenReturn(new MockResponse()
//                        .setResponseCode(400)
//                        .setBody("{\"error\": \"The world is ending, cannot process request\"}")
//                        .setBodyDelay(DELAY_SEC, TimeUnit.SECONDS)
//                );



//        RESTMockServer.whenGET(pathContains("stats"))
//                .thenAnswer(new MockAnswer() {
//                    @Override
//                    public MockResponse answer(RecordedRequest request) {
//                        String path = request.getPath();
//                        String id = path.substring(path.lastIndexOf("/") + 1);
//
//                        try {
//                            String body = mocksFileParser.readJsonFile(id + ".xml");
//
//                            return new MockResponse()
//                                    .setResponseCode(200)
//                                    .setHeader("Content-type", "application/xml")
//                                    .setBody(body)
//                                    .setBodyDelay(DELAY_SEC, TimeUnit.SECONDS);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            throw new RuntimeException("The mock server setup failed");
//                        }
//                    }
//                });


        RESTMockServer.whenPOST(pathContains("/api/register"))
                .thenAnswer(new MockAnswer() {
                    @Override
                    public MockResponse answer(RecordedRequest request) {


                        try {
                            String body = mocksFileParser.readJsonFile("signin.json");


                            return new MockResponse()
                                    .setResponseCode(ConectionUtils.CODE_201)
                                    .setHeader("Content-type", "application/json")
                                    .setBody(body)
                                    .setBodyDelay(DELAY_SEC, TimeUnit.SECONDS);

                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RuntimeException("The mock server setup failed");
                        }
                    }
                });

        RESTMockServer.whenPOST(pathContains("/api/login"))
                .thenAnswer(new MockAnswer() {
                    @Override
                    public MockResponse answer(RecordedRequest request) {


                        try {
                            String body = mocksFileParser.readJsonFile("signin.json");


                            return new MockResponse()
                                    .setResponseCode(ConectionUtils.CODE_200)
                                    .setHeader("Content-type", "application/json")
                                    .setBody(body)
                                    .setBodyDelay(DELAY_SEC, TimeUnit.SECONDS);

                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new RuntimeException("The mock server setup failed");
                        }
                    }
                });

    }


    public static String getUrl() {
        return RESTMockServer.getUrl();
    }

}
