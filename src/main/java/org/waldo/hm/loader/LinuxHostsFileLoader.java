package org.waldo.hm.loader;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waldo.hm.model.Host;

import java.io.*;

/**
 * User: wangyin
 * Date: 13-6-2
 * Time: 下午9:20
 */
public class LinuxHostsFileLoader {

    private static final Logger logger = LoggerFactory.getLogger(LinuxHostsFileLoader.class);

    private static final String DEFAULT_LINUX_HOSTS_FILE = "/etc/hosts";

    public void loadHosts() {
        File hostsFile = new File(DEFAULT_LINUX_HOSTS_FILE);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(hostsFile)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (StringUtils.isNotBlank(line)) {
                    logger.debug(Host.getInstance(line).toString());
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("Occurs error while load hosts file.", e);
        } catch (IOException e) {
            logger.error("Occurs error while load hosts file.", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // Ignore
                }
            }
        }
    }

    public static void main(String[] args) {
        new LinuxHostsFileLoader().loadHosts();
    }

}
