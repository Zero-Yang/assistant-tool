/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package util.mybatis;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.io.OutputFormat;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.google.common.io.Resources;

import util.mybatis.utils.Log;

/**
 * 
 * 启动代码生成器.
 *
 * @author huanglicong
 * @version V1.0
 */
public class CodeGeneratorRuner {

	public static void main(String[] args) throws Exception {

		boolean overwrite = true;
		String generatorConfig = CodeGeneratorRuner.class.getClassLoader().getResource("generatorConfig.xml").getFile();
		URL url = Resources.getResource("generatorConfig.xml");
        URLConnection openConnection = url.openConnection();
        InputStream inputStream = openConnection.getInputStream();
        OutputFormat format = new OutputFormat();
        format.setEncoding("UTF-8");
		List<String> warnings = new ArrayList<String>();
		Log.print("加载代码生成器配置," + generatorConfig);
//		File conifgFile = new File(url);

		ConfigurationParser configurationParser = new ConfigurationParser(warnings);
		Configuration configuration = configurationParser.parseConfiguration(new InputStreamReader(inputStream, "UTF-8"));
		DefaultShellCallback shellCallback = new DefaultShellCallback(overwrite);
		MyBatisGenerator batisGenerator = new MyBatisGenerator(configuration, shellCallback, warnings);
		batisGenerator.generate(new CodeProgressCallback());
	}

}
