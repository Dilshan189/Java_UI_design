package vote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import vote.user;



public class VotePanelUI extends JFrame {
    private boolean hasVoted = false;

    // Define parties and colors
    private final String[][] parties = {
        {"Democratic Party", "#1E90FF"},
        {"Republican Party", "#FF0000"},
        {"Green Party", "#32CD32"},
        {"Liberal Party", "#FFA500"},
        {"Freedom Party", "#800080"}
    };

    // Map of party to candidate list
    private final HashMap<String, String[]> partyCandidates = new HashMap<>();

    public VotePanelUI() {
    setTitle("National Election Voting System");
    setSize(550, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Setup candidates
    partyCandidates.put("Democratic Party", new String[]{"Alice Johnson", "Bob Smith"});
    partyCandidates.put("Republican Party", new String[]{"John Doe", "Emma Brown"});
    partyCandidates.put("Green Party", new String[]{"Tom Green", "Lily Eco"});
    partyCandidates.put("Liberal Party", new String[]{"Anna Ray", "Mark Free"});
    partyCandidates.put("Freedom Party", new String[]{"Chris Power", "Sarah Vote"});

    showPartyPanel();

    // ✅ ADD THIS LINE
    setVisible(true);
}


    // Show first screen: list of parties
    private void showPartyPanel() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Vote for Your Preferred Party", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(parties.length, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        for (String[] party : parties) {
            String partyName = party[0];
            Color partyColor = Color.decode(party[1]);

            JButton voteButton = new JButton(partyName);
            voteButton.setBackground(partyColor);
            voteButton.setForeground(Color.WHITE);
            voteButton.setFont(new Font("Arial", Font.BOLD, 16));
            voteButton.setFocusPainted(false);

            voteButton.addActionListener(e -> showCandidatePanel(partyName));

            buttonPanel.add(voteButton);
        }

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Show second screen: list of candidates in the selected party
    private void showCandidatePanel(String partyName) {
        if (hasVoted) {
            JOptionPane.showMessageDialog(this,
                    "You have already voted!",
                    "Vote Denied",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Candidates - " + partyName, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JPanel candidatePanel = new JPanel();
        candidatePanel.setLayout(new GridLayout(0, 1, 5, 5));
        candidatePanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        ButtonGroup group = new ButtonGroup();
        JRadioButton selectedButton[] = new JRadioButton[1];  

        for (String candidate : partyCandidates.get(partyName)) {
            JRadioButton radioButton = new JRadioButton(candidate);
            radioButton.setFont(new Font("Arial", Font.PLAIN, 16));
            group.add(radioButton);
            candidatePanel.add(radioButton);

            radioButton.addActionListener(e -> selectedButton[0] = radioButton);
        }

        JButton confirmButton = new JButton("Confirm Vote");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setBackground(Color.GREEN);
        confirmButton.setForeground(Color.BLACK);

        confirmButton.addActionListener(e -> {
            if (selectedButton[0] == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a candidate!",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                String selectedCandidate = selectedButton[0].getText();
                hasVoted = true;
                JOptionPane.showMessageDialog(this,
                        "Thank you for voting for: " + selectedCandidate,
                        "Vote Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                showThankYouScreen(selectedCandidate);
            }
        });

        add(title, BorderLayout.NORTH);
        add(candidatePanel, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void showThankYouScreen(String candidate) {
    getContentPane().removeAll();
    setLayout(new BorderLayout());

    JLabel thankYou = new JLabel("<html><center>Thank you for voting!<br>Your vote for <b>" + candidate + "</b> has been recorded.<br><br>Returning to main screen...</center></html>", SwingConstants.CENTER);
    thankYou.setFont(new Font("Arial", Font.BOLD, 18));
    thankYou.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));

    add(thankYou, BorderLayout.CENTER);
    revalidate();
    repaint();

    // 🕒 Timer to go back after 5 seconds (5000 milliseconds)
    
    Timer timer = new Timer(5000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((Timer)e.getSource()).stop(); 
            user();
            

        }

        private void user() {
            
            
            user s = new user();
            s.setVisible(true);
            dispose();
     
            
            
            
            
            
        }
    });
    timer.setRepeats(false); // Run only once
    timer.start();
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(VotePanelUI::new);
    }
}
