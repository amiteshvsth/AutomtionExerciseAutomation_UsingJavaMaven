FROM maven:3.9.6-eclipse-temurin-21

# Install required system libs
RUN apt-get update && \
    apt-get install -y \
        wget \
        curl \
        unzip \
        gnupg \
        ca-certificates \
        fonts-liberation \
        libgtk-3-0 \
        libdbus-glib-1-2 \
        libasound2 \
        libx11-xcb1 \
        libxt6 \
        libxcomposite1 \
        libxcursor1 \
        libxdamage1 \
        libxrandr2 \
        libgbm1 \
        libnss3 \
        libatk1.0-0 \
        libatk-bridge2.0-0 \
        libdrm2 \
        libxss1 \
        libxext6 \
        xdg-utils && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Install Chrome
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-linux.gpg && \
    echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-linux.gpg] http://dl.google.com/linux/chrome/deb/ stable main" \
    > /etc/apt/sources.list.d/google.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Install Firefox (XZ archive, no bzip2 dependency)
ENV FIREFOX_VERSION=128.0

RUN wget -q https://ftp.mozilla.org/pub/firefox/releases/${FIREFOX_VERSION}/linux-x86_64/en-US/firefox-${FIREFOX_VERSION}.tar.xz -O firefox.tar.xz && \
    tar -xJf firefox.tar.xz && \
    mv firefox /opt/firefox && \
    ln -s /opt/firefox/firefox /usr/bin/firefox && \
    rm firefox.tar.xz

# Install Geckodriver
ENV GECKO_VERSION=0.35.0

RUN wget -q https://github.com/mozilla/geckodriver/releases/download/v${GECKO_VERSION}/geckodriver-v${GECKO_VERSION}-linux64.tar.gz -O geckodriver.tar.gz && \
    tar -xzf geckodriver.tar.gz && \
    mv geckodriver /usr/local/bin/ && \
    rm geckodriver.tar.gz

# Verify
RUN google-chrome --version && firefox --version && geckodriver --version