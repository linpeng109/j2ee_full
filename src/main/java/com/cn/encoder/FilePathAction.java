package com.cn.encoder;

import com.cn.encoder.exceptions.PathCheckFailureException;
import com.cn.encoder.utils.FilePathCheck;
import org.apache.commons.io.FilenameUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.RIPEMD128Digest;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.util.HashMap;

public class FilePathAction {

    /**
     * 依赖注入获取picture文件的输出路径
     */
    private String outputPath;

    /**
     * 依赖注入获取输出文件名前缀
     */
    private String prefix;


    /**
     * @param message 输入消息
     * @return 输出消息
     * @throws PathCheckFailureException 路径检查错误
     */
    public Message<String> execute(Message<String> message)
            throws PathCheckFailureException {
        /**
         * 处理input系列参数
         */
        MessageHeaders headers = message.getHeaders();

        String input = (String) headers.get("input");
        if (FilePathCheck.checkFile(input) == FilePathCheck.CHECK_FAILURE) {
            throw new PathCheckFailureException("The Input is Error!");
        }
        String inputFileMainName = FilenameUtils.getBaseName(input);
        String inputExtension = FilenameUtils.getExtension(input);

        /**
         * 处理output系统参数
         */
        if (FilePathCheck.checkDir(this.getOutputPath()) == FilePathCheck.CHECK_FAILURE) {
            throw new PathCheckFailureException("The output is Error!");
        }
        Digest digest = new RIPEMD128Digest();
        byte[] srcBytes = FilenameUtils.getBaseName(input).getBytes();
        digest.update(srcBytes, 0, srcBytes.length);
        byte[] resultBytes = new byte[digest.getDigestSize()];
        digest.doFinal(resultBytes, 0);
        String outputFileMainName = new String(Hex.encode(resultBytes));
        this.setOutputPath(FilenameUtils.getFullPath(this.getOutputPath()));
        String outputPathAndOutputFileMainName = this.getOutputPath()
                + outputFileMainName;

        /**
         * 处理系列变量
         */
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("input", input);
        map.put("outputPath", outputPath);
        map.put("prefix", prefix);
        map.put("outputFileMainName", outputFileMainName);
        map.put("outputPathAndOutputFileMainName",
                outputPathAndOutputFileMainName);
        map.put("inputFileMainName", inputFileMainName);
        map.put("inputExtension", inputExtension);

        /**
         * 生成新的消息
         */
        Message<String> result = MessageBuilder.withPayload("")
                .copyHeaders(map).build();
        return result;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
