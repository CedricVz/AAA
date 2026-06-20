import type { Metadata, Viewport } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: {
    default: "Acoustic AI Assistant",
    template: "%s | Acoustic AI Assistant"
  },
  description: "Free privacy-first tools to understand room acoustics, diagnose noise problems and plan practical treatment.",
  applicationName: "Acoustic AI Assistant",
  keywords: [
    "room acoustics",
    "acoustic treatment calculator",
    "noise analysis",
    "podcast room",
    "home studio acoustics",
    "AI acoustic assistant"
  ],
  manifest: "/site.webmanifest",
  robots: {
    index: true,
    follow: true
  }
};

export const viewport: Viewport = {
  width: "device-width",
  initialScale: 1,
  themeColor: "#07111f"
};

export default function RootLayout({ children }: Readonly<{ children: React.ReactNode }>) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}
