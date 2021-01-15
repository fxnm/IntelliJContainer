# IntelliJContainer

![GitHub Workflow Status (branch)](https://img.shields.io/github/workflow/status/fxnm/IntelliJContainer/Main%20Build/main?label=Main%20Build)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/fxnm/IntelliJContainer)
![GitHub](https://img.shields.io/github/license/fxnm/IntelliJContainer)

IntelliJ Container is a Docker image for running intellij IDEA. With this container it is possible to run ui test of
Intellij plugins in Intellij IDEA.

This is especially true for github actions.

## Background

When trying to run Intellij IDEA in an environment without a graphical user interface, Intellij IDEA can not start for
ui tests `runIdeForUiTests`. This problem also occurs with the task `runide`.

This problem can be solved by using (under Linux) a program like xvfb, which simulates a display. If you now start tasks
like runIde or runIdeForUiTests with xvfb, they execute, but the ide does not start.

Because when the Ide is starting for the first time, the terms and conditions must be accepted and the sharing of data
must be allowed or prohibited. Only then does the Ide start normally.

This can be done with the following commands:

**Disable IntelliJ data sharing**

````
RUN set -x \
  && dir=/root/.local/share/JetBrains/consentOptions \
  && mkdir -p "$dir" \
  && echo -n "rsch.send.usage.stat:1.1:0:$(date +%s)000" > "$dir/accepted"

````

**Accept End User Agreement / privacy policy**

````
RUN set -x \
  && dir="/root/.java/.userPrefs/jetbrains/_!(!!cg\"p!(}!}@\"j!(k!|w\"w!'8!b!\"p!':!e@==" \
  && mkdir -p "$dir" \
  && echo '<?xml version="1.0" encoding="UTF-8" standalone="no"?>\n\
<!DOCTYPE map SYSTEM "http://java.sun.com/dtd/preferences.dtd">\n\
<map MAP_XML_VERSION="1.0">\n\
  <entry key="accepted_version" value="2.1"/>\n\
  <entry key="eua_accepted_version" value="1.1"/>\n\
  <entry key="privacyeap_accepted_version" value="2.1"/>\n\
</map>' > "$dir/prefs.xml" \
  && cat "$dir/prefs.xml"
````

After running these commands IntelliJ IDEA starts without any popup and is available for ui testing.

## User

IntelliJ Container comes with JDK 11, gradle 6.8 and XVFB pre-installed.

Fur use add the option `container: fxnm/intellij.docker:latest`. This will select the container and the basic 
settings are taken from the docker image.

After that, you can work normally, like with an ordinary ubuntu.

Example

````
jobs:
    uiTest:
    name: Ui Test
    runs-on: ubuntu-latest
    container: fxnm/intellij.docker:latest
    steps:

      # Check out current repository - not needed
      - name: Fetch Sources
        uses: actions/checkout@v2

      - name: Grant execute permission for gradlew
        run: chmod +x gradlew

      # Run Ui Test
      - name: Run Ui Test
        run: xvfb-run ./gradlew runUiTest
````

For further example view:

- [CodeTester-IDEA](https://github.com/fxnm/CodeTester-IDEA)

## License

This code is released under a MIT License, as specified in the
accompanying [LICENSE FILE](https://github.com/fxnm/IntelliJContainer/blob/main/LICENSE).
