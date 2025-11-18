import java.util.Scanner;

public class CareerRecommender {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your key skill:");
        String skill = sc.nextLine().trim().toLowerCase();

        System.out.println("How comfortable are you with programming?");
        System.out.println("1. Not Comfortable");
        System.out.println("2. Somewhat Comfortable");
        System.out.println("3. Very Comfortable");
        int comfort = readIntSafe(sc, 1, 3);

        System.out.println("\nWhich of these tasks sounds most appealing?");
        System.out.println("1. Building Applications");
        System.out.println("2. Testing & Quality Assurance");
        System.out.println("3. Designing Systems / Architecture");
        System.out.println("4. Managing Projects");
        int task = readIntSafe(sc, 1, 4);

        System.out.println("\nDo you enjoy problem solving under pressure (e.g., incident response or debugging production issues)?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int pressure = readIntSafe(sc, 1, 2);

        String career = recommendCareer(skill, comfort, task, pressure);

        System.out.println("\n=== Career Recommendation ===");
        System.out.println(career);
        sc.close();
    }

    private static int readIntSafe(Scanner sc, int min, int max) {
        int val;
        while (true) {
            String line = sc.nextLine().trim();
            try {
                val = Integer.parseInt(line);
                if (val < min || val > max) {
                    System.out.printf("Please enter a number between %d and %d: ", min, max);
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a valid number: ");
            }
        }
    }

    private static String recommendCareer(String skill, int comfort, int task, int pressure) {
        // Normalize skill into simple categories
        boolean hasSQL = skill.contains("sql") || skill.contains("database") || skill.contains("db");
        boolean hasJava = skill.contains("java");
        boolean hasPython = skill.contains("python");
        boolean hasDevOps = skill.contains("devops") || skill.contains("aws") || skill.contains("azure");

        // Core logic: map common combos to career suggestions
        // Priority: task interest -> skill match -> comfort level -> pressure tolerance
        if (task == 4) { // Managing Projects
            if (hasSQL && comfort >= 3) {
                // SQL + very comfortable + managing projects -> managerial technical + data tilt
                return "Data Engineering Manager / Technical Project Manager (Data-focused)\n"
                        + "Why: Strong SQL skill + high programming comfort + preference for managing projects. "
                        + "Great fit leading data teams, owning delivery, and coordinating engineering work.";
            }
            if (hasJava || hasPython) {
                return "Technical Project Manager (Engineering)\n"
                        + "Why: Programming experience + interest in managing projects — ideal for leading engineering squads.";
            }
            // fallback for managing projects
            return "IT Project Coordinator / Delivery Manager\n"
                    + "Why: Good fit if you prefer people/process ownership with technical stakeholders; "
                    + "consider upskilling in a tech stack for higher impact.";
        }

        if (task == 1) { // Building Applications
            if (hasSQL && comfort >= 2) {
                return "Backend Developer / Data Engineer\n"
                        + "Why: SQL + programming comfort = strong backend/data roles building systems that store and process data.";
            }
            if (hasJava) return "Java Developer";
            if (hasPython) return "Python Developer";
            return "Software Developer (Entry) — consider sharpening a language/framework and building projects.";
        }

        if (task == 2) { // Testing & QA
            if (comfort >= 2) {
                return "QA Engineer (Automation)\n"
                        + "Why: Some programming comfort plus interest in QA suits automation testing and test frameworks.";
            } else {
                return "QA Analyst (Manual)\n"
                        + "Why: Good fit to start in test design, processes and QA workflow.";
            }
        }

        if (task == 3) { // Designing Systems / Architecture
            if (comfort >= 3) {
                if (hasSQL || hasDevOps) {
                    return "Solutions Architect / Data Architect\n"
                            + "Why: Strong technical comfort + familiarity with databases or cloud -> architecture roles.";
                }
                return "Systems Architect\n"
                        + "Why: High programming comfort and interest in system design makes this a strong target.";
            } else {
                return "Technical Designer / Business Analyst (Technical)\n"
                        + "Why: Consider ramping up technical depth to move into architecture.";
            }
        }

        // Catch-all fallback (shouldn't happen)
        return "No exact match found — but based on your inputs you might try roles like:\n"
                + "- Technical Project Manager\n- Data Engineer\n- Backend Developer\n\nTip: Normalize skill names (e.g., 'sql' or 'postgres') and rerun to get a tighter match.";
    }
}
