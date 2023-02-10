package by.a1qa.tdtask.apisteps;

import by.a1qa.tdtask.constants.Endpoints;
import by.a1qa.tdtask.constants.Parameters;
import by.a1qa.tdtask.utils.HostNameUtil;
import by.a1qa.tdtask.utils.ImageUtil;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.Base64;

@UtilityClass
public class PostSteps extends BaseSteps {

    public static Response getTokenByVariant(String variant) {
        return baseRequest()
                .queryParam(Parameters.VARIANT, variant)
                .post(Endpoints.GET_TOKEN);
    }

    public static Response getJsonTestListByProjectId(String projectId) {
        return baseRequest()
                .queryParam(Parameters.PROJECT_ID, projectId)
                .post(Endpoints.GET_TEST_LIST_JSON);
    }

    public static Response addNewTestRecord(String SID, String projectName, String testName, String methodName) {
        return baseRequest()
                .queryParam(Parameters.SESSION_ID, SID)
                .queryParam(Parameters.PROJECT_NAME, projectName)
                .queryParam(Parameters.TEST_NAME, testName)
                .queryParam(Parameters.METHOD_NAME, methodName)
                .queryParam(Parameters.ENV, HostNameUtil.getHostname())
                .post(Endpoints.ADD_TEST_RECORD);
    }

    public static Response updateTestStatus(String testId, String status) {
        return baseRequest()
                .queryParam(Parameters.TEST_ID, testId)
                .queryParam(Parameters.STATUS, status)
                .post(Endpoints.ADD_TEST_STATUS);
    }

    public static Response addLog(String testId, String content) {
        return baseRequest()
                .queryParam(Parameters.TEST_ID, testId)
                .queryParam(Parameters.CONTENT, content)
                .post(Endpoints.ADD_TEST_LOG);
    }

    public static Response addPicture(String testId, byte[] bytes) {
        byte[] smallPicture = ImageUtil.reduceImage(bytes);
        return baseRequest()
                .queryParam(Parameters.TEST_ID, testId)
                .queryParam(Parameters.CONTENT_TYPE, Parameters.IMAGE)
                .queryParam(Parameters.CONTENT, Base64.getEncoder().encodeToString(smallPicture))
                .post(Endpoints.ADD_TEST_ATTACHMENT);
    }
}
