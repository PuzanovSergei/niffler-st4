package guru.qa.niffler.db.logging;

import io.qameta.allure.attachment.AttachmentData;
import io.qameta.allure.attachment.AttachmentProcessor;
import io.qameta.allure.attachment.DefaultAttachmentProcessor;
import io.qameta.allure.attachment.FreemarkerAttachmentRenderer;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

public class JsonAllureAppender {

    private final String templateName = "json-query.ftl";
    private final AttachmentProcessor<AttachmentData> attachmentProcessor = new DefaultAttachmentProcessor();

    @SneakyThrows
    public void logJson(String json) {
        if (StringUtils.isNoneEmpty(json)) {
            JsonAttachment attachment = new JsonAttachment("Json body: ", json);
            attachmentProcessor.addAttachment(attachment, new FreemarkerAttachmentRenderer(templateName));
        }
    }
}
