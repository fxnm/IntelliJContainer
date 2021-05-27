FROM ubuntu:latest


ARG GRADLE_VERSION

# Check if Gradle Version Argument is set
RUN if ["$GRADLE_VERSION" == ""] ; \
    then echo Build arg 'GRADLE_VERSION' not set && FAILED ; \
    fi


# Update and upgrade ubuntu
RUN apt-get update
RUN apt-get upgrade -y


# Disable IntelliJ data sharing
RUN set -x \
  && dir=/root/.local/share/JetBrains/consentOptions \
  && mkdir -p "$dir" \
  && echo -n "rsch.send.usage.stat:1.1:0:$(date +%s)000" > "$dir/accepted"


# Accept End User Agreement/privacy policy
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


# Install xcfb
RUN apt-get install -y xvfb


# Install gradle
RUN apt-get install -y wget zip default-jdk
RUN wget "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip" -O gradle.zip
RUN unzip -d /opt gradle.zip
RUN rm gradle.zip
ENV PATH=$PATH:/opt/gradle-$GRADLE_VERSION/bin

# Intall curl
RUN apt-get install curl
