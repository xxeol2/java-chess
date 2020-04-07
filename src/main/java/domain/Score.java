package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.board.Rank;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.team.Team;

public class Score {
	public static Map<Team, Double> calculateScore(List<Rank> ranks, Team... teams) {
		Map<Team, Double> score = new HashMap<>();
		Arrays.stream(teams)
			.forEach(team -> score.put(team, calculateScoreByTeam(ranks, team)));
		return score;
	}

	private static double calculateScoreByTeam(List<Rank> ranks, Team team) {
		double sum = ranks.stream()
			.map(rank -> calculateScore(rank, team))
			.reduce(0.0, Double::sum);

		return applyPawnScore(ranks, team, sum);
	}

	private static double calculateScore(Rank rank, Team team) {
		List<Piece> pieces = rank.getPieces();
		return pieces.stream()
			.filter(piece -> piece.isTeam(team))
			.map(Piece::getScore)
			.reduce(0.0, Double::sum);
	}

	private static double applyPawnScore(List<Rank> ranks, Team team, double sum) {
		List<Piece> pawn = new ArrayList<>();

		ranks.stream()
			.map(rank -> findPawn(rank, team))
			.map(pawn::addAll)
			.close();

		if (hasSameColumnPawn(pawn)) {
			sum += pawn.size() * Pawn.PAWN_SCORE_WHEN_HAS_SAME_COLUMN;
		}
		return sum;
	}

	private static List<Piece> findPawn(Rank rank, Team team) {
		List<Piece> pieces = rank.getPieces();

		return pieces.stream()
			.filter(value -> value instanceof Pawn)
			.filter(value -> value.isTeam(team))
			.collect(Collectors.toList());
	}

	private static boolean hasSameColumnPawn(List<Piece> pawns) {
		int distinctCount = (int)pawns.stream()
			.map(pawn -> pawn.getPosition().getColumn())
			.distinct()
			.count();

		return pawns.size() != distinctCount;
	}
}