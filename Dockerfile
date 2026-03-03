FROM maven:3.9.6-eclipse-temurin-21

# -----------------------------
# Install system dependencies
# -----------------------------
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

# -----------------------------
# Install Google Chrome
# -----------------------------
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-linux.gpg && \
    echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-linux.gpg] http://dl.google.com/linux/chrome/deb/ stable main" \
    > /etc/apt/sources.list.d/google.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# -----------------------------
# Install Firefox (REAL binary, NOT snap)
# -----------------------------
RUN wget -q https://ftp.mozilla.org/pub/firefox/releases/latest/linux-x86_64/en-US/firefox-latest.tar.bz2 -O firefox.tar.bz2 && \
    tar -xjf firefox.tar.bz2 && \
    mv firefox /opt/firefox && \
    ln -s /opt/firefox/firefox /usr/bin/firefox && \
    rm firefox.tar.bz2

# -----------------------------
# Install Geckodriver
# -----------------------------
RUN wget -q https://github.com/mozilla/geckodriver/releases/latest/download/geckodriver-v0.35.0-linux64.tar.gz && \
    tar -xzf geckodriver-v0.35.0-linux64.tar.gz && \
    mv geckodriver /usr/local/bin/ && \
    rm geckodriver-v0.35.0-linux64.tar.gz

# Verify installations (fails build if broken)
RUN google-chrome --version && \
    firefox --version && \
    geckodriver --version