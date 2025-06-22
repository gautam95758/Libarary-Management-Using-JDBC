// Enhanced checkbox interaction
const checkboxGroup = document.getElementById('checkboxGroup');
const checkbox = document.getElementById('availableForLoan');

checkboxGroup.addEventListener('click', function() {
    checkbox.checked = !checkbox.checked;
    updateCheckboxStyle();
});

checkbox.addEventListener('change', updateCheckboxStyle);

function updateCheckboxStyle() {
    if (checkbox.checked) {
        checkboxGroup.classList.add('checked');
    } else {
        checkboxGroup.classList.remove('checked');
    }
}

// Form submission with enhanced animations
document.getElementById("bookForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const bookId = document.getElementById("bookId").value;
    const title = document.getElementById("bookTitle").value;
    const author = document.getElementById("author").value;
    const genre = document.getElementById("genre").value;
    const available = document.getElementById("availableForLoan").checked;

    // Create enhanced book card
    const bookCard = `
        <div class="book-header">
            <div class="book-icon">📖</div>
            <div class="book-title">${title}</div>
        </div>
        <div class="book-details">
            <div class="detail-item">
                <div class="detail-label">Book ID</div>
                <div class="detail-value">${bookId}</div>
            </div>
            <div class="detail-item">
                <div class="detail-label">Author</div>
                <div class="detail-value">${author}</div>
            </div>
            <div class="detail-item">
                <div class="detail-label">Genre</div>
                <div class="detail-value">${genre}</div>
            </div>
            <div class="detail-item">
                <div class="detail-label">Status</div>
                <div class="detail-value">
                    <span class="available-badge ${available ? 'available-yes' : 'available-no'}">
                        ${available ? '✓ Available' : '✗ Not Available'}
                    </span>
                </div>
            </div>
        </div>
    `;

    // Animate the output
    const outputSection = document.getElementById('outputSection');
    const bookCardElement = document.getElementById('bookCard');

    bookCardElement.innerHTML = bookCard;
    outputSection.classList.add('show');

    // Add success feedback
    const submitBtn = document.querySelector('.submit-btn');
    const originalText = submitBtn.textContent;
    submitBtn.textContent = '✓ Book Added Successfully!';
    submitBtn.style.background = 'linear-gradient(135deg, #48bb78, #38a169)';

    setTimeout(() => {
        submitBtn.textContent = originalText;
        submitBtn.style.background = 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)';
    }, 2000);

    // Scroll to output
    outputSection.scrollIntoView({ behavior: 'smooth', block: 'center' });
});

// Floating particles effect
function createFloatingParticle() {
    const particle = document.createElement('div');
    particle.style.cssText = `
        position: fixed;
        width: 4px;
        height: 4px;
        background: rgba(255, 255, 255, 0.6);
        border-radius: 50%;
        pointer-events: none;
        left: ${Math.random() * 100}vw;
        top: 100vh;
        z-index: -1;
        animation: float ${3 + Math.random() * 4}s linear infinite;
    `;

    document.body.appendChild(particle);

    setTimeout(() => {
        particle.remove();
    }, 7000);
}

// Create particles periodically
setInterval(createFloatingParticle, 2000);

// Initialize checkbox style on page load
document.addEventListener('DOMContentLoaded', function() {
    updateCheckboxStyle();
});