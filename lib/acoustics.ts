export type RoomUse = "podcast" | "music" | "streaming" | "office" | "bedroom";
export type NoiseSource = "traffic" | "voices" | "echo" | "appliances" | "unknown";

export interface RoomInput {
  length: number;
  width: number;
  height: number;
  hardSurfaceRatio: number;
  roomUse: RoomUse;
  noiseSource: NoiseSource;
  budget: "low" | "medium" | "high";
}

export interface AcousticDiagnosis {
  volumeM3: number;
  wallAreaM2: number;
  recommendedCoverageM2: number;
  roomScore: number;
  priority: string;
  actions: string[];
}

const coverageByUse: Record<RoomUse, number> = {
  podcast: 0.3,
  music: 0.35,
  streaming: 0.25,
  office: 0.18,
  bedroom: 0.15
};

function clamp(value: number, min: number, max: number): number {
  return Math.min(Math.max(value, min), max);
}

export function diagnoseRoom(input: RoomInput): AcousticDiagnosis {
  const length = Math.max(input.length, 0);
  const width = Math.max(input.width, 0);
  const height = Math.max(input.height, 0);
  const hardSurfaceRatio = clamp(input.hardSurfaceRatio, 0, 1);

  const volumeM3 = length * width * height;
  const wallAreaM2 = 2 * height * (length + width);
  const baseCoverage = wallAreaM2 * coverageByUse[input.roomUse];
  const surfaceAdjustment = 0.75 + hardSurfaceRatio * 0.5;
  const recommendedCoverageM2 = baseCoverage * surfaceAdjustment;

  const echoPenalty = input.noiseSource === "echo" ? 22 : 8;
  const hardSurfacePenalty = hardSurfaceRatio * 35;
  const sizePenalty = volumeM3 > 90 ? 10 : volumeM3 > 45 ? 5 : 0;
  const roomScore = Math.round(clamp(100 - echoPenalty - hardSurfacePenalty - sizePenalty, 18, 92));

  const actions: string[] = [];

  if (input.noiseSource === "traffic") {
    actions.push("Prioritize window seals, heavy curtains and door gaps before adding foam.");
  } else if (input.noiseSource === "voices") {
    actions.push("Treat shared walls and door leakage; absorption alone will not fully block voices.");
  } else if (input.noiseSource === "echo") {
    actions.push("Place absorption at first-reflection points and on the largest opposing bare walls.");
  } else if (input.noiseSource === "appliances") {
    actions.push("Identify vibration paths and isolate the appliance before treating the room broadly.");
  } else {
    actions.push("Run the guided clap test and a short sound sample to identify the dominant problem.");
  }

  if (hardSurfaceRatio > 0.65) {
    actions.push("Add soft furnishings, a rug or curtains before purchasing a large panel kit.");
  }

  if (input.roomUse === "podcast" || input.roomUse === "streaming") {
    actions.push("Treat the wall behind the microphone and the nearest side reflections first.");
  }

  if (input.roomUse === "music") {
    actions.push("Reserve part of the budget for bass trapping; thin foam mainly affects high frequencies.");
  }

  if (input.budget === "low") {
    actions.push("Start with reversible improvements and compare before-and-after measurements.");
  }

  const priority = input.noiseSource === "traffic" || input.noiseSource === "voices"
    ? "Sound isolation first"
    : "Room absorption first";

  return {
    volumeM3: Number(volumeM3.toFixed(2)),
    wallAreaM2: Number(wallAreaM2.toFixed(2)),
    recommendedCoverageM2: Number(recommendedCoverageM2.toFixed(1)),
    roomScore,
    priority,
    actions: actions.slice(0, 4)
  };
}
