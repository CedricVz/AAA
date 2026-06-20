"use client";

import { FormEvent, useMemo, useState } from "react";
import {
  diagnoseRoom,
  type NoiseSource,
  type RoomInput,
  type RoomUse
} from "@/lib/acoustics";

const initialRoom: RoomInput = {
  length: 4,
  width: 3,
  height: 2.5,
  hardSurfaceRatio: 0.6,
  roomUse: "podcast",
  noiseSource: "echo",
  budget: "low"
};

export default function HomePage() {
  const [room, setRoom] = useState<RoomInput>(initialRoom);
  const [submitted, setSubmitted] = useState(false);
  const diagnosis = useMemo(() => diagnoseRoom(room), [room]);

  function updateNumber(field: "length" | "width" | "height", value: string) {
    setRoom((current) => ({ ...current, [field]: Number(value) || 0 }));
  }

  function submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setSubmitted(true);
  }

  return (
    <main>
      <header className="siteHeader">
        <a className="brand" href="#top" aria-label="Acoustic AI Assistant home">
          <span className="brandMark">AAA</span>
          <span>Acoustic AI Assistant</span>
        </a>
        <a className="headerAction" href="#diagnosis">Analyse my room</a>
      </header>

      <section className="hero" id="top">
        <div className="heroCopy">
          <p className="eyebrow">PRIVACY-FIRST ACOUSTIC INTELLIGENCE</p>
          <h1>Understand your room.<br />Improve your sound.</h1>
          <p className="heroText">
            A free intelligent assistant for creators, musicians and everyday spaces.
            Diagnose acoustic problems, estimate treatment and make smarter purchases.
          </p>
          <div className="heroActions">
            <a className="primaryButton" href="#diagnosis">Start free diagnosis</a>
            <a className="secondaryButton" href="#how-it-works">How it works</a>
          </div>
          <p className="privacyNote">Audio analysis will be processed locally whenever the device supports it.</p>
        </div>

        <div className="signalCard" aria-label="Product preview">
          <div className="signalHeader">
            <span>Room signal</span>
            <span className="liveBadge">LOCAL</span>
          </div>
          <div className="wave" aria-hidden="true">
            {[34, 62, 44, 84, 52, 74, 38, 68, 46, 78, 42, 58].map((height, index) => (
              <span key={index} style={{ height: `${height}%` }} />
            ))}
          </div>
          <div className="signalResult">
            <div><strong>Echo</strong><span>Likely issue</span></div>
            <div><strong>Medium</strong><span>Priority</span></div>
            <div><strong>Private</strong><span>Processing</span></div>
          </div>
        </div>
      </section>

      <section className="trustStrip" aria-label="Product principles">
        <span>No account required</span>
        <span>Local-first AI</span>
        <span>Transparent recommendations</span>
        <span>Mobile ready</span>
      </section>

      <section className="diagnosisSection" id="diagnosis">
        <div className="sectionIntro">
          <p className="eyebrow">ROOM DIAGNOSIS MVP</p>
          <h2>Build your first acoustic plan</h2>
          <p>This initial expert engine turns room data into practical priorities without a paid AI API.</p>
        </div>

        <div className="diagnosisGrid">
          <form className="diagnosisForm" onSubmit={submit}>
            <div className="fieldRow">
              <label>Length (m)<input min="1" max="40" step="0.1" type="number" value={room.length} onChange={(e) => updateNumber("length", e.target.value)} /></label>
              <label>Width (m)<input min="1" max="40" step="0.1" type="number" value={room.width} onChange={(e) => updateNumber("width", e.target.value)} /></label>
              <label>Height (m)<input min="1" max="12" step="0.1" type="number" value={room.height} onChange={(e) => updateNumber("height", e.target.value)} /></label>
            </div>

            <label>
              Main use
              <select value={room.roomUse} onChange={(e) => setRoom({ ...room, roomUse: e.target.value as RoomUse })}>
                <option value="podcast">Podcast / voice recording</option>
                <option value="streaming">Streaming / video</option>
                <option value="music">Music / home studio</option>
                <option value="office">Office / calls</option>
                <option value="bedroom">Bedroom / everyday use</option>
              </select>
            </label>

            <label>
              Main problem
              <select value={room.noiseSource} onChange={(e) => setRoom({ ...room, noiseSource: e.target.value as NoiseSource })}>
                <option value="echo">Echo and reflections</option>
                <option value="traffic">Traffic from outside</option>
                <option value="voices">Voices through walls or doors</option>
                <option value="appliances">Appliances or vibration</option>
                <option value="unknown">I am not sure</option>
              </select>
            </label>

            <label>
              Hard surfaces: {Math.round(room.hardSurfaceRatio * 100)}%
              <input type="range" min="0" max="1" step="0.05" value={room.hardSurfaceRatio} onChange={(e) => setRoom({ ...room, hardSurfaceRatio: Number(e.target.value) })} />
            </label>

            <fieldset>
              <legend>Budget</legend>
              <div className="choiceRow">
                {(["low", "medium", "high"] as const).map((budget) => (
                  <label className="choice" key={budget}>
                    <input type="radio" name="budget" checked={room.budget === budget} onChange={() => setRoom({ ...room, budget })} />
                    <span>{budget}</span>
                  </label>
                ))}
              </div>
            </fieldset>

            <button className="primaryButton formButton" type="submit">Generate acoustic plan</button>
          </form>

          <aside className={`resultCard ${submitted ? "resultReady" : ""}`} aria-live="polite">
            <div className="scoreRing"><strong>{diagnosis.roomScore}</strong><span>/100</span></div>
            <p className="resultLabel">Estimated room score</p>
            <h3>{diagnosis.priority}</h3>
            <div className="metrics">
              <div><strong>{diagnosis.volumeM3} m³</strong><span>Room volume</span></div>
              <div><strong>{diagnosis.recommendedCoverageM2} m²</strong><span>Suggested absorption</span></div>
            </div>
            <ol>
              {diagnosis.actions.map((action) => <li key={action}>{action}</li>)}
            </ol>
            <p className="disclaimer">This consumer estimate is educational and does not replace a calibrated acoustic survey.</p>
          </aside>
        </div>
      </section>

      <section className="featureSection" id="how-it-works">
        <div className="sectionIntro">
          <p className="eyebrow">INTELLIGENCE WITHOUT TOKEN COSTS</p>
          <h2>The product roadmap</h2>
        </div>
        <div className="featureGrid">
          <article><span>01</span><h3>Sound Detective</h3><p>Classify likely traffic, voices, appliances and environmental sound directly on the device.</p></article>
          <article><span>02</span><h3>Guided Clap Test</h3><p>Estimate room decay and compare before-and-after improvements with clear limitations.</p></article>
          <article><span>03</span><h3>Treatment Planner</h3><p>Build essential, balanced and pro plans linked to relevant affiliate products.</p></article>
          <article><span>04</span><h3>Telegram Operator</h3><p>Manage content, products, analytics and approvals from a private mobile conversation.</p></article>
        </div>
      </section>

      <footer>
        <div><strong>Acoustic AI Assistant</strong><p>Smarter rooms. Better sound.</p></div>
        <p>Built as a privacy-first applied AI product.</p>
      </footer>
    </main>
  );
}
