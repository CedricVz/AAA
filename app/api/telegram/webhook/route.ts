import { NextRequest, NextResponse } from "next/server";

type TelegramUpdate = {
  message?: {
    text?: string;
    chat: { id: number };
    from?: { id: number; first_name?: string };
  };
};

async function sendTelegramMessage(chatId: number, text: string): Promise<void> {
  const botToken = process.env.TELEGRAM_BOT_TOKEN;
  if (!botToken) throw new Error("Telegram bot token is not configured");

  const response = await fetch(`https://api.telegram.org/bot${botToken}/sendMessage`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ chat_id: chatId, text })
  });

  if (!response.ok) throw new Error("Telegram message delivery failed");
}

export async function POST(request: NextRequest) {
  const configuredSecret = process.env.TELEGRAM_WEBHOOK_SECRET;
  const receivedSecret = request.headers.get("x-telegram-bot-api-secret-token");

  if (!configuredSecret || receivedSecret !== configuredSecret) {
    return NextResponse.json({ ok: false }, { status: 401 });
  }

  const update = (await request.json()) as TelegramUpdate;
  const message = update.message;
  if (!message?.from || !message.text) {
    return NextResponse.json({ ok: true, ignored: true });
  }

  const ownerUserId = process.env.TELEGRAM_OWNER_USER_ID;
  const ownerChatId = process.env.TELEGRAM_OWNER_CHAT_ID;
  const authorized = String(message.from.id) === ownerUserId && String(message.chat.id) === ownerChatId;

  if (!authorized) {
    return NextResponse.json({ ok: true, ignored: true });
  }

  const command = message.text.trim().toLowerCase();

  if (command === "/status" || command === "status") {
    await sendTelegramMessage(
      message.chat.id,
      "Acoustic AI Assistant is online. Content editing and analytics commands will be enabled in controlled phases."
    );
  } else {
    await sendTelegramMessage(
      message.chat.id,
      "Command received. For now use /status. Editing actions will require previews and confirmation."
    );
  }

  return NextResponse.json({ ok: true });
}
