package com.example.demo.job.csvjob.processor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.User;

//校验csv是否复合要求
@Component
public class ValidatingProcessor implements ItemProcessor<User, User> {

    private final ResourcePatternResolver resourcePatternResolver;

    private static final Log LOG = LogFactory.getLog(ValidatingProcessor.class);
    
    @Value("${commonp.elasticsearch.host}")
    private String eshost;

    @Value("${commonp.elasticsearch.port}")
    private String esport;

//    @Value("${spring.batch.rootPath}")
//    private String rootPath;
//    @Value("${spring.batch.headerPath}")
//    private String headerPath;

    private static String SUCCEED;
    private static String FAILED;
    private static String COMMIT;

//    @Autowired
    public ValidatingProcessor(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }


    @Override
    public User process(final User user) throws Exception{
        System.out.println("处理类输出:"+user.toString());
//        Settings settings = Settings.builder()
//                .put("cluster.name", "elasticsearch")
//                .put("client.transport.sniff", true)
//                .put("client.transport.ignore_cluster_name", true)
//                .put("client.transport.ping_timeout", "20s")
//                .build();
//        Client client =new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(eshost), Integer.parseInt(esport)));
//        GetResponse response = client.prepareGet("movies", "movie", "1")
//                .setOperationThreaded(false)    // 线程安全
//                .get();
//        System.out.println("ES返回数值"+response.getSourceAsString());
        return user;
    }

    
    private String readMd5(Resource md5Resource) throws IOException {
        InputStreamReader read = new InputStreamReader(new FileInputStream(md5Resource.getFile()));
        BufferedReader bufferedReader = new BufferedReader(read);
        return bufferedReader.readLine().replaceAll("[\u0000-\u001f]", "").replaceAll("[\uFEFF]", "");
    }

    private void writeFile(File file, String code) {
        try {
            switch (code) {
                case "0000": {
                    checkDir(file, SUCCEED);//准备文件夹

                    File md5 = new File(file.getPath().replace(".csv", ".md5"));
//                File zip = new File(file.getPath().replace(".csv", ".zip"));
                    Files.copy(file.toPath(), new File(SUCCEED + file.getName().replace(".csv", "") + "/"
                            + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING); //复制

                    Files.copy(md5.toPath(), new File(SUCCEED + file.getName().replace(".csv", "") + "/"
                            + file.getName().replace(".csv", ".md5")).toPath(), StandardCopyOption.REPLACE_EXISTING); //复制

                    //复制后删除
                    md5.delete();
                    file.delete();
//                zip.delete();
                    LOG.info("检查成功的文件:" + file.getName());
                    break;
                }
                case "-1": {
                    checkDir(file, COMMIT);//准备文件夹

                    checkDir(file, SUCCEED);
                    File md5 = new File(file.getPath().replace(".csv", ".md5"));
                    Files.copy(file.toPath(), new File(COMMIT + file.getName().replace(".csv", "") + "/" + file.getName()).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    Files.copy(md5.toPath(), new File(COMMIT + file.getName().replace(".csv", "") + "/" + file.getName().replace(".csv", ".md5")).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);

                    //复制后删除
                    md5.delete();
                    file.delete();

                    LOG.info("空文件处理:" + file.getName() + " code:" + code);
                    break;
                }
                default: {
                    checkDir(file, FAILED);//准备文件夹

                    File md5 = new File(file.getPath().replace(".csv", ".md5"));
//                File zip = new File(file.getPath().replace(".csv", ".zip"));

                    Files.copy(file.toPath(), new File(FAILED + file.getName().replace(".csv", "") + "/" + file.getName()).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                    Files.copy(md5.toPath(), new File(FAILED + file.getName().replace(".csv", "") + "/" + file.getName().replace(".csv", ".md5")).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);

                    //复制后删除
                    md5.delete();
                    file.delete();
//                zip.delete();
                    FileWriter writer = new FileWriter(FAILED + file.getName().replace(".csv", "") + "/reason");
                    BufferedWriter bw = new BufferedWriter(writer);
                    bw.write(code);
                    bw.close();
                    writer.close();

                    LOG.info("检查失败的文件:" + file.getName() + " code:" + code);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void checkDir(File file, String flag) {
        File makeDir = new File(flag + file.getName().replace(".csv", ""));
        if (!makeDir.exists())
            makeDir.mkdirs();
    }

    /**
     * 采用BufferedInputStream方式读取文件行数
     *
     * @param filename
     * @return
     * @throws IOException
     */

    public int count(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        byte[] c = new byte[1024];
        int count = 0;
        int readChars;
        while ((readChars = is.read(c)) != -1) {
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n')
                    ++count;
            }
        }
        is.close();
        return count;
    }

    /**
     * 采用BufferedReader方式读取总行数
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    private int getTotalLines2(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String strLine = br.readLine();
        int totalLines = 0;
        while (strLine != null) {
            totalLines++;
            strLine = br.readLine();
        }
        br.close();
        return totalLines;
    }


    /**
     * RandomAccessFile 获取文件最后一行
     */
    public static String readLastLine(File file) throws IOException {
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            return null;
        }
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            long len = raf.length();
            if (len == 0L) {
                return "";
            } else {
                long pos = len - 1;
                while (pos > 0) {
                    pos--;
                    raf.seek(pos);
                    if (raf.readByte() == '\n') {
                        break;
                    }
                }
                if (pos == 0) {
                    raf.seek(0);
                }
                byte[] bytes = new byte[(int) (len - pos)];
                raf.read(bytes);
                return new String(bytes).replaceAll("[\u0000-\u001f]", "").replaceAll("[\uFEFF]", "");

            }
        } catch (FileNotFoundException e) {
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (Exception e2) {
                }
            }
        }
        return null;
    }

}
