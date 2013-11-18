/*
 * Copyright (C) 2013 GenieCode
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

package net.geniecode.ttr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ExecShell {

	public static enum SHELL_CMD {
		check_su_binary(new String[] { "/system/xbin/which", "su" }), ;

		String[] command;

		SHELL_CMD(String[] command) {
			this.command = command;
		}
	}

	public ArrayList<String> executeCommand(SHELL_CMD shellCmd) {
		String line = null;
		ArrayList<String> fullResponse = new ArrayList<String>();
		Process localProcess = null;

		try {
			localProcess = Runtime.getRuntime().exec(shellCmd.command);
		} catch (Exception e) {
			return null;
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(
				localProcess.getInputStream()));

		try {
			while ((line = in.readLine()) != null) {
				fullResponse.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fullResponse;
	}

}
