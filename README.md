# Gambling Plugin

A lightweight and modular **Minecraft gambling plugin** that introduces a full in-game economy based on converting resources into currency and using that currency to gamble in various ways.

## 🎮 Features

### 💰 Resource-to-Money System

Turn vanilla resources into virtual money via commands or GUIs:

* Coal
* Iron
* Gold
* Emeralds
* Diamonds
* (More can be added via config)

Each resource has a configurable exchange value.

### 🎲 Gambling Games

Spend your earned money to try your luck:

* **Roulette** – Bet on colors or numbers. (In development)
* **Ride the Bus** – High-risk, high-reward mini‑game. (In development)
* **Slot Machines** – Spin for randomized payouts. (In development)
* **Coin Flip** – Flip a coin to win or lose money. (In development)
* More games are planned!

### 📈 Future Plans

* **Stocks & Crypto simulation**

    * Live or semi-live stock price integration (if API-friendly)
    * Buy, hold, sell system
    * Risk levels and random events

## 🛠️ Installation

1. Download the latest plugin release.
2. Place the `.jar` file into your server's `plugins` folder.
3. Restart the server.
4. Configure in the generated config file.

## 📜 Commands (Planned)

* `/exchange` – Convert resources to money.
* `/balance` – Check your gambling balance.
* `/gamble` – Open the main gambling menu. (In development)

## ⚙️ Permissions (Planned)

* `gambling.exchange` – Allows users to convert resources to money.
* `gambling.balance` – Allows users to check their own or other player's balance
* `gambling.play` – Parent Permission for all gambling games. (In development)
* `gambling.play.<game>` – Is used to revoke access to a specific game. (In development)


## 🧩 Configuration

A full YAML config allows changing:

* Resource exchange values
* Minimum and maximum bets (in development)
* More Config coming soon!

## 💡 Goals

The goal is to create a fun, expandable, and server‑friendly gambling system that integrates smoothly with survival or economy servers.

## 🚫 Real-Life Gambling Disclaimer

This plugin is meant **purely for entertainment inside Minecraft**.

**If you gamble in real life, never gamble more than you can happily afford to lose.** Gambling carries financial risks and can be addictive. Treat it as entertainment, not a way to make money.

## 🤝 Contributing

Pull requests, feature ideas, and bug reports are welcome! Feel free to open an issue. More Information [here](CONTRIBUTING.md).

## 📄 License

This project is licensed under the [**Apache 2.0 License**](LICENSE).
