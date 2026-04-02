import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import * as api from '../api/gameApi';

const initialState = {
  playerName: '',
  turnCount: 0,
  isComplete: false,
  board: Array(25).fill(null).map((_, index) => ({
    index,
    revealed: false,
    isTreasure: false,
    proximityValue: null,
  })),
  selectedPositions: [],
  loading: false,
  error: null,
};

export const startGame = createAsyncThunk(
  'game/startGame',
  async (playerName) => {
    const response = await api.startGame(playerName);
    return response;
  }
);

export const revealPositions = createAsyncThunk(
  'game/revealPositions',
  async ({ playerName, positions }, { rejectWithValue }) => {
    try {
      const response = await api.revealPositions(playerName, positions);
      return response;
    } catch (error) {
      return rejectWithValue(error.response?.data || 'Failed to reveal positions');
    }
  }
);

const gameSlice = createSlice({
  name: 'game',
  initialState,
  reducers: {
    selectPosition: (state, action) => {
      const index = action.payload;
      if (state.selectedPositions.length < 3 && !state.selectedPositions.includes(index)) {
        state.selectedPositions.push(index);
      }
    },
    deselectPosition: (state, action) => {
      const index = action.payload;
      state.selectedPositions = state.selectedPositions.filter(i => i !== index);
    },
    clearSelection: (state) => {
      state.selectedPositions = [];
    },
    resetGame: (state) => {
      return initialState;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(startGame.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(startGame.fulfilled, (state, action) => {
        state.loading = false;
        state.playerName = action.payload.playerName;
        state.turnCount = action.payload.turnCount;
        state.isComplete = action.payload.isComplete;
        
        // Update board with revealed positions
        action.payload.revealedPositions.forEach(pos => {
          const index = pos.row * 5 + pos.col;
          state.board[index] = {
            index,
            revealed: true,
            isTreasure: pos.isTreasure,
            proximityValue: pos.proximityValue,
          };
        });
      })
      .addCase(startGame.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message;
      })
      .addCase(revealPositions.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(revealPositions.fulfilled, (state, action) => {
        state.loading = false;
        state.turnCount = action.payload.turnCount;
        state.isComplete = action.payload.isComplete;
        
        // Update board with newly revealed positions
        action.payload.newlyRevealed.forEach(pos => {
          const index = pos.row * 5 + pos.col;
          state.board[index] = {
            index,
            revealed: true,
            isTreasure: pos.isTreasure,
            proximityValue: pos.proximityValue,
          };
        });
        
        // Clear selection
        state.selectedPositions = [];
      })
      .addCase(revealPositions.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload || action.error.message;
        state.selectedPositions = [];
      });
  },
});

export const { selectPosition, deselectPosition, clearSelection, resetGame } = gameSlice.actions;
export default gameSlice.reducer;
