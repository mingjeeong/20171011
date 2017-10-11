package work.model.dto;

public class BooksDto {

	private String bname;
	private String author;
	private String publisher;
	private String publishdate;
	private String isbn;
	private String loc;
	
	
	/**
	 * �⺻ �ʱ�ȭ ������
	 */
	public BooksDto() {
		super();
	}

/**
 * ��ü �ʱ�ȭ ������
 * @param bname
 * @param author
 * @param publisher
 * @param publishdate
 * @param isbn
 * @param loc
 */
	public BooksDto(String bname, String author, String publisher, String publishdate, String isbn, String loc) {
		
		this.bname = bname;
		this.author = author;
		this.publisher = publisher;
		this.publishdate = publishdate;
		this.isbn = isbn;
		this.loc = loc;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("���� :"+bname);
		builder.append("\n");
		builder.append("���� : "+author);
		builder.append("\n");
		builder.append("���ǻ� : "+publisher);
		builder.append("\n");
		builder.append("����⵵ : "+publishdate);
		builder.append("\n");
		builder.append("û����ȣ: "+isbn);
		builder.append("\n");
		builder.append("ISBN: : "+loc);
		return builder.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bname == null) ? 0 : bname.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((loc == null) ? 0 : loc.hashCode());
		result = prime * result + ((publishdate == null) ? 0 : publishdate.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BooksDto other = (BooksDto) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublishdate() {
		return publishdate;
	}
	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
}
